package com.transactionmgmt.users.ms_users.service.dto;

public record CreateClientDto (
        String name,
        String gender,
        int age,
        String identification,
        String address,
        String phoneNumber,
        String password
) {
}
