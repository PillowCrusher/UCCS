package com.UpkeepAccService.UCCS.events.entity;

import com.UpkeepAccService.UCCS.events.entity.AccountEvent;
import com.UpkeepAccService.UCCS.events.entity.CreateAccount;

import javax.json.JsonObject;
import java.time.Instant;
import java.util.UUID;

public class AcceptAccount extends AccountEvent {

    private final UUID accountId;

    public AcceptAccount(final UUID accountId) {
        this.accountId = accountId;
    }

    public AcceptAccount(final UUID accountId, Instant instant) {
        super(instant);
        this.accountId = accountId;
    }

    public AcceptAccount(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("accountId")),Instant.parse(jsonObject.getString("instant")));
    }

    public UUID getAccountId() {
        return accountId;
    }
}
