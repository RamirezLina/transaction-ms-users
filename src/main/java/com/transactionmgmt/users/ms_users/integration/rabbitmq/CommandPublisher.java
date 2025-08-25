package com.transactionmgmt.users.ms_users.integration.rabbitmq;

import com.transactionmgmt.users.ms_users.integration.rabbitmq.command.CreateAccountCommand;

public interface CommandPublisher {

    void sendCreateAccount(CreateAccountCommand evt);
}
