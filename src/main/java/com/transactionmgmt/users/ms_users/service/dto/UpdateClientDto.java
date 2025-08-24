package com.transactionmgmt.users.ms_users.service.dto;

public record UpdateClientDto(
        String name,
        String gender,
        int age,
        String identification,
        String address,
        String phoneNumber
        ) {
}
