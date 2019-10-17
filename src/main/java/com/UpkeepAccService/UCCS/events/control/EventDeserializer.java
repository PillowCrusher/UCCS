package com.UpkeepAccService.UCCS.events.control;

import com.UpkeepAccService.UCCS.events.entity.AccountEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class EventDeserializer implements Deserializer<AccountEvent> {

    private static final Logger LOGGER = Logger.getLogger(EventDeserializer.class.getName());

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public AccountEvent deserialize(String s, byte[] bytes) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            final JsonObject jsonObject = Json.createReader(input).readObject();
            final Class<? extends AccountEvent> eventClass = (Class<? extends AccountEvent>) Class.forName(jsonObject.getString("class"));
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.severe("Could not deserialize event: " + e.getMessage());
            throw new SerializationException("Could not deserialize event ",e);
        }
        return null;
    }

    @Override
    public void close() {

    }
}
