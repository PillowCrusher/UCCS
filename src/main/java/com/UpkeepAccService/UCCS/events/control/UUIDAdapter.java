package com.UpkeepAccService.UCCS.events.control;

import javax.json.bind.adapter.JsonbAdapter;
import java.util.UUID;

public class UUIDAdapter implements JsonbAdapter<UUID,String> {

    @Override
    public String adaptToJson(UUID uuid) throws Exception {
        return uuid.toString();
    }

    @Override
    public UUID adaptFromJson(String s) throws Exception {
        return UUID.fromString(s);
    }
}
