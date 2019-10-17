package com.UpkeepAccService.UCCS.events.control;

import com.UpkeepAccService.UCCS.events.entity.AccountEvent;
import com.UpkeepAccService.UCCS.events.entity.CreateAccount;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.ProducerFencedException;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class EventProducer {

    private Producer<String, AccountEvent> producer;
    private String topic;

    @Inject
    Properties kafkaProp;

    @Inject
    Logger LOGGER;

    @PostConstruct
    private void init() {
        kafkaProp.put("transactional.id", UUID.randomUUID().toString());
        producer = new KafkaProducer<String, AccountEvent>(kafkaProp);
        topic = kafkaProp.getProperty("orders.topic");
        producer.initTransactions();
    }

    public void publish(AccountEvent... events) {
        try {
            producer.beginTransaction();
            send(events);
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            producer.close();
        } catch (KafkaException e) {
            producer.abortTransaction();
        }
    }

    private void send(AccountEvent... events) {
        for (final AccountEvent event : events) {
            final ProducerRecord<String, AccountEvent> record = new ProducerRecord<>(topic, event);
            LOGGER.info("publisihing = " + record);
            producer.send(record);
        }
    }

    @PreDestroy
    public void close() {
        producer.close();
    }
}
