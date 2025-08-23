package com.transactionmgmt.users.ms_users.service.dto;


public record ClientDto(
        Long clientId,
        String name,
        String gender,
        int age,
        String identification,
        String address,
        String phone) {
}
