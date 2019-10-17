package com.UpkeepAccService.UCCS.events.control;

import com.UpkeepAccService.UCCS.events.entity.AccountEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;

public class EventSerializer implements Serializer<AccountEvent> {

    private static final Logger LOGGER = Logger.getLogger(EventSerializer.class.getName());

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(final String topic,final AccountEvent event) {
        try {
            if (event == null)
                return null;

            final JsonbConfig config = new JsonbConfig()
                    .withAdapters(new UUIDAdapter())
                    .withSerializers(new EventJsonSerializer());

            final Jsonb jsonb = JsonbBuilder.create(config);

            return jsonb.toJson(event,AccountEvent.class).getBytes(StandardCharsets.UTF_8);

        } catch (Exception e) {
            LOGGER.severe("Could not seriali  ze event: " + e.getMessage());
            throw new SerializationException("Could not serialize event", e);
        }
    }

    @Override
    public void close() {

    }
}
