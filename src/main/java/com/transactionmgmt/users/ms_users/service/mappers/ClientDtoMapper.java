package com.transactionmgmt.users.ms_users.service.mappers;


import com.transactionmgmt.users.ms_users.domain.client.Client;
import com.transactionmgmt.users.ms_users.service.dto.ClientDto;
import com.transactionmgmt.users.ms_users.service.dto.CreateClientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientDtoMapper {
    
    ClientDto toResponse (Client client);
    List<ClientDto> toResponseList(List<Client> clientList);
    Client toModel(CreateClientDto dto);
    
}
