package com.transactionmgmt.users.ms_users.domain.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phoneNumber;
}
