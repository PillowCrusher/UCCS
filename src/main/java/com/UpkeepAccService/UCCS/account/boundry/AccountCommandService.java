package com.UpkeepAccService.UCCS.account.boundry;

import com.UpkeepAccService.UCCS.events.control.EventProducer;
import com.UpkeepAccService.UCCS.events.entity.AcceptAccount;
import com.UpkeepAccService.UCCS.events.entity.AccountInfo;
import com.UpkeepAccService.UCCS.events.entity.CreateAccount;
import com.UpkeepAccService.UCCS.account.control.AccountOrders;

import javax.inject.Inject;
import java.util.UUID;

public class AccountCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    AccountOrders accountOrders;

    public void registerAccount(AccountInfo accountInfo) {
        eventProducer.publish(new CreateAccount(accountInfo));
    }

    void accountConfirmed(final UUID accountId) {
        final AccountInfo accountInfo = accountOrders.get(accountId).getAccountInfo();
        eventProducer.publish(new AcceptAccount(accountId));
    }


}
