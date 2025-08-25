package com.transactionmgmt.users.ms_users.domain.client;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {
    
    private Long clientId;
    private String password;
    private Boolean status;

    public void setDefaultValues() {
        this.status = true;
    }

    public void softDelete(){
        this.status = false;
    }
}
