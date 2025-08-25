package com.transactionmgmt.users.ms_users.integration.rabbitmq.command;

public record CreateAccountCommand(
        Long numeroCuenta,
        AccountType tipoCuenta,
        Double saldoInicial,
        Long clienteId
) {}
