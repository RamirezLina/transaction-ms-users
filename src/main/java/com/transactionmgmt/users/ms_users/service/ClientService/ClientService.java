package com.transactionmgmt.users.ms_users.service.ClientService;

import com.transactionmgmt.users.ms_users.service.dto.ClientDto;
import com.transactionmgmt.users.ms_users.service.dto.CreateClientDto;
import com.transactionmgmt.users.ms_users.service.dto.UpdateClientDto;

import java.util.List;

public interface ClientService {
    void createClient(CreateClientDto dto);
    List<ClientDto> getAllClients();
    ClientDto updateClient(Long clienteId, UpdateClientDto clienteRequestDTO);
    void deleteClient(Long clienteId);
    ClientDto getClientById(Long clienteId);
}
