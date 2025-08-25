package com.transactionmgmt.users.ms_users.domain.client;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Person {
    private String name;
    private GenderType gender;
    private int age;
    private String identification;
    private String address;
    private String phoneNumber;
}
