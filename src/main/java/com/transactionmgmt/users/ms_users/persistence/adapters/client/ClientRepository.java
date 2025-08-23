package com.transactionmgmt.users.ms_users.persistence.adapters.client;


import com.transactionmgmt.users.ms_users.domain.client.Client;

import java.math.BigInteger;
import java.util.List;

public interface ClientRepository {

    void saveClient(Client newClient);
    Client findByClientId(Long clientId);
    List<Client> getAllClients();
    
}
