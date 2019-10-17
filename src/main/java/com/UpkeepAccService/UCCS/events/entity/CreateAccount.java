package com.UpkeepAccService.UCCS.events.entity;

import javax.json.JsonObject;
import java.time.Instant;

public class CreateAccount extends AccountEvent {

    private AccountInfo accountInfo;

    public CreateAccount(final AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public CreateAccount(final AccountInfo accountInfo, Instant instant) {
        super(instant);
        this.accountInfo = accountInfo;
    }

    public CreateAccount(JsonObject jsonObject) {
        this(new AccountInfo(jsonObject.getJsonObject("accountInfo")),Instant.parse(jsonObject.getString("instant")));
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }
}
