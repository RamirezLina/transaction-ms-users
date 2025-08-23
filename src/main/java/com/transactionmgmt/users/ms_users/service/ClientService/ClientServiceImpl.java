package com.transactionmgmt.users.ms_users.service.ClientService;

import com.transactionmgmt.users.ms_users.domain.client.Client;
import com.transactionmgmt.users.ms_users.persistence.adapters.client.ClientRepository;
import com.transactionmgmt.users.ms_users.service.dto.ClientDto;
import com.transactionmgmt.users.ms_users.service.dto.CreateClientDto;
import com.transactionmgmt.users.ms_users.service.dto.UpdateClientDto;
import com.transactionmgmt.users.ms_users.service.mappers.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientDtoMapper mapper;

    @Override
    public void createClient(CreateClientDto dto) {
        Client newClient = mapper.toModel(dto);
        newClient.setStatus(true);
        repository.saveClient(newClient);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return mapper.toResponseList(repository.getAllClients());
    }

    @Override
    public ClientDto updateClient(Long clienteId, UpdateClientDto clienteRequestDTO) {
        Client clientToUpdate = repository.findByClientId(clienteId);

        if (clienteRequestDTO.name() != null) {
            clientToUpdate.setName(clienteRequestDTO.name());
        }
        if (clienteRequestDTO.gender() != null) {
            clientToUpdate.setGender(clienteRequestDTO.gender());
        }
        if (clienteRequestDTO.age() > 0) {
            clientToUpdate.setAge(clienteRequestDTO.age());
        }
        if (clienteRequestDTO.identification() != null) {
            clientToUpdate.setIdentification(clienteRequestDTO.identification());
        }
        if (clienteRequestDTO.address() != null) {
            clientToUpdate.setAddress(clienteRequestDTO.address());
        }
        if (clienteRequestDTO.phone() != null) {
            clientToUpdate.setPhoneNumber(clienteRequestDTO.phone());
        }
        repository.saveClient(clientToUpdate);
        return mapper.toResponse(clientToUpdate);
    }

    @Override
    public void deleteClient(Long clienteId) {
        Client clientToDelete = repository.findByClientId(clienteId);
        clientToDelete.setStatus(false);
        repository.saveClient(clientToDelete);
        
    }
}
