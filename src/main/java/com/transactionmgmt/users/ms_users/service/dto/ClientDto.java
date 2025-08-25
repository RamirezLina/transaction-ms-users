package com.transactionmgmt.users.ms_users.service.dto;


import com.transactionmgmt.users.ms_users.domain.client.GenderType;

public record ClientDto(
        Long clientId,
        String name,
        GenderType gender,
        int age,
        String identification,
        String address,
        String phoneNumber) {
}
