package com.transactionmgmt.users.ms_users.domain.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {
    
    private Long clientId;
    private String password;
    private Boolean status;
}
