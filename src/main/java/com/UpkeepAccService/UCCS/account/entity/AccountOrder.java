package com.UpkeepAccService.UCCS.account.entity;

import com.UpkeepAccService.UCCS.events.entity.AccountInfo;


public class AccountOrder {

    private AccountState state;
    private AccountInfo accountInfo;

    public void create(final AccountInfo accountInfo) {
        state = AccountState.CREATED;
        this.accountInfo = accountInfo;
    }

    public void confirm() { state = AccountState.CONFIRMED; }
    public void delete() { state = AccountState.DELETED; }

    public AccountState getState() {
        return state;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public enum AccountState {
        CREATED,
        CONFIRMED,
        DELETED
    }

}
