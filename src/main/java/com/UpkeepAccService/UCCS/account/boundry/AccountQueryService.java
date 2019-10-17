package com.UpkeepAccService.UCCS.account.boundry;

import com.UpkeepAccService.UCCS.account.control.AccountOrders;
import com.UpkeepAccService.UCCS.account.entity.AccountOrder;

import javax.inject.Inject;
import java.util.UUID;

public class AccountQueryService {

    @Inject
    AccountOrders accountOrders;

    public AccountOrder getAccount(final UUID accountId) {
        return accountOrders.get(accountId);
    }

}
