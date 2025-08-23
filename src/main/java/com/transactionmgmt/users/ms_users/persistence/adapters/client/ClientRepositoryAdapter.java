package com.transactionmgmt.users.ms_users.persistence.adapters.client;

import com.transactionmgmt.users.ms_users.domain.client.Client;
import com.transactionmgmt.users.ms_users.persistence.entities.ClientEntity;
import com.transactionmgmt.users.ms_users.persistence.mappers.ClientEntityMapper;
import com.transactionmgmt.users.ms_users.persistence.repositories.ClientDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ClientRepositoryAdapter implements ClientRepository {
    
    private final ClientDataRepository dataRepository;
    private final ClientEntityMapper mapper;
    
    @Override
    public void saveClient(Client newClient) {
        dataRepository.save(mapper.toEntity(newClient));
    }

    @Override
    public Client findByClientId(Long clientId) {
        ClientEntity entity = dataRepository.findByClientId(clientId).orElseThrow();
        return mapper.toModel(entity);
    }

    @Override
    public List<Client> getAllClients() {
        return mapper.getModels(dataRepository.findAll())
                .stream().filter(Client::getStatus).toList();
    }
    


}
