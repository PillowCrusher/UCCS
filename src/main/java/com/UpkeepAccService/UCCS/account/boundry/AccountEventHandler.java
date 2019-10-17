package com.UpkeepAccService.UCCS.account.boundry;

import com.UpkeepAccService.UCCS.events.control.EventConsumer;
import com.UpkeepAccService.UCCS.events.entity.AcceptAccount;
import com.UpkeepAccService.UCCS.events.entity.AccountEvent;
import com.UpkeepAccService.UCCS.events.entity.CreateAccount;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Properties;
import java.util.logging.Logger;

@Singleton
@Startup
public class AccountEventHandler {

    private EventConsumer eventConsumer;

    @Resource
    ManagedExecutorService mes;

    @Inject
    Properties kafkaProperties;

    @Inject
    Event<AccountEvent> events;

    @Inject
    AccountCommandService accountCommandService;

    @Inject
    Logger logger;

    public void handle(@Observes CreateAccount event) {
        accountCommandService.registerAccount(event.getAccountInfo());
    }

    public void handle(@Observes AcceptAccount event) {
        accountCommandService.accountConfirmed(event.getAccountId());
    }

    @PostConstruct
    private void initConsumer() {
        kafkaProperties.put("group.id","account-handler");
        String account = kafkaProperties.getProperty("account.topic");

        eventConsumer = new EventConsumer(kafkaProperties, ev -> {
            logger.info("ifiring = " + ev);
            events.fire(ev);
        }, account);

        mes.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }
}
