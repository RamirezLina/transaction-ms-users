package com.transactionmgmt.users.ms_users.persistence.adapters.client;

import com.transactionmgmt.users.ms_users.domain.client.Client;
import com.transactionmgmt.users.ms_users.persistence.entities.ClientEntity;
import com.transactionmgmt.users.ms_users.persistence.mappers.ClientEntityMapper;
import com.transactionmgmt.users.ms_users.persistence.repositories.ClientDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ClientRepositoryAdapter implements ClientRepository {
    
    private final ClientDataRepository dataRepository;
    private final ClientEntityMapper mapper;
    
    @Override
    public Client saveClient(Client newClient) {
        ClientEntity entity = dataRepository.save(mapper.toEntity(newClient));
        return mapper.toModel(entity); 
    }

    @Override
    public Optional<Client> findByClientId(Long clientId) {
        return dataRepository.findByClientId(clientId)
                .filter(ClientEntity::getStatus)
                .map(mapper::toModel);
               
    }

    @Override
    public List<Client> getAllClients() {
        return mapper.getModels(dataRepository.findAll())
                .stream().filter(Client::getStatus)
                .toList();
    }
    


}
