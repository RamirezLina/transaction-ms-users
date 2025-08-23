package com.transactionmgmt.users.ms_users.persistence.mappers;

import com.transactionmgmt.users.ms_users.domain.client.Client;
import com.transactionmgmt.users.ms_users.persistence.entities.ClientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {

    Client toModel(ClientEntity dto);
    ClientEntity toEntity (Client dto);
    List<Client> getModels(List<ClientEntity> entitiesList);
    
    
}
