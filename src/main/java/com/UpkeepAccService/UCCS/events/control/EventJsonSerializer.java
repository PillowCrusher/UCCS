package com.UpkeepAccService.UCCS.events.control;

import com.UpkeepAccService.UCCS.events.entity.AccountEvent;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class EventJsonSerializer implements JsonbSerializer<AccountEvent> {
    @Override
    public void serialize(final AccountEvent event, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
        jsonGenerator.writeStartObject();
        jsonGenerator.write("class",event.getClass().getCanonicalName());
        serializationContext.serialize("data",event,jsonGenerator);
        jsonGenerator.writeEnd();
        jsonGenerator.close();
    }
}
