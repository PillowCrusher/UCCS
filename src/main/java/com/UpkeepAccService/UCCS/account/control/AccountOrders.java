package com.UpkeepAccService.UCCS.account.control;

import com.UpkeepAccService.UCCS.events.entity.AccountInfo;
import com.UpkeepAccService.UCCS.events.entity.CreateAccount;
import com.UpkeepAccService.UCCS.account.entity.AccountOrder;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class AccountOrders {

    private Map<UUID, AccountOrder> accountOrders = new ConcurrentHashMap<>();

    public AccountOrder get(final UUID accountId) {
        return accountOrders.get(accountId);
    }

    public void apply(@Observes CreateAccount event) {
        accountOrders.putIfAbsent(event.getAccountInfo().getOrderId(),new AccountOrder());
        applyFor(event.getAccountInfo().getOrderId(),o -> o.create(event.getAccountInfo()));
    }



    public AccountInfo getAccountInfo() {
        return null;
    }

    private void applyFor(final UUID accountId, final Consumer<AccountOrder> consumer) {

    }
}
