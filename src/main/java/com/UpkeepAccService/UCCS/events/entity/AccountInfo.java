package com.UpkeepAccService.UCCS.events.entity;

import javax.json.JsonObject;
import java.util.UUID;

public class AccountInfo {

    private final UUID accountId;
    private final String email;
    private final String username;
    private final String password;

    public AccountInfo(UUID accountId, String email, String username, String password) {
        this.accountId = accountId;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public AccountInfo(JsonObject jsonObject) {
        this(UUID.fromString(jsonObject.getString("accountId")),
                jsonObject.getString("email"),
                jsonObject.getString("username"),
                jsonObject.getString("password"));
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
