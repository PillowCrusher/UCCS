package com.UpkeepAccService.UCCS.events.control;

import com.UpkeepAccService.UCCS.events.entity.AccountEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.io.StringWriter;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class EventConsumer implements Runnable {

    private final KafkaConsumer<String, AccountEvent> consumer;
    private final Consumer<AccountEvent> eventConsumer;
    private final AtomicBoolean closed = new AtomicBoolean();

    public EventConsumer(Properties kafkaProperties, Consumer<AccountEvent> eventConsumer, String... topics) {
        this.eventConsumer = eventConsumer;
        consumer = new KafkaConsumer<String, AccountEvent>(kafkaProperties);
        consumer.subscribe(asList(topics));
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                consume();
            }
        } catch (WakeupException e) {
            e.printStackTrace();
        }
        finally {
            consumer.close();
        }
    }

    private void consume() {
        ConsumerRecords<String, AccountEvent> records = consumer.poll(Long.MAX_VALUE);
        for(ConsumerRecord<String, AccountEvent> record : records) {
            eventConsumer.accept(record.value());
        }
    }

    public void stop() {
        closed.set(true);
        consumer.wakeup();
    }

}
