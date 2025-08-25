package com.transactionmgmt.users.ms_users.persistence.adapters.client;


import com.transactionmgmt.users.ms_users.domain.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Client saveClient(Client newClient);
    Optional<Client> findByClientId(Long clientId);
    List<Client> getAllClients();
    
}
