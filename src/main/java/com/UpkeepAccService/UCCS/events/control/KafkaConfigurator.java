package com.UpkeepAccService.UCCS.events.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import java.io.IOException;
import java.util.Properties;

@ApplicationScoped
public class KafkaConfigurator {

    private Properties kafkaProperties;

    @PostConstruct
    private void initProperties() {
        try {
            kafkaProperties = new Properties();
            kafkaProperties.load(KafkaConfigurator.class.getResourceAsStream("/kafka.properties"));
            //TODO setup prop file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Produces
    @RequestScoped
    public Properties exposeKafkaProperties() {
        final Properties properties = new Properties();
        properties.putAll(kafkaProperties);
        return properties;
    }
}
