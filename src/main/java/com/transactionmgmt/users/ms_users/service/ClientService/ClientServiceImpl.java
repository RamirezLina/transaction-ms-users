package com.transactionmgmt.users.ms_users.service.ClientService;

import com.transactionmgmt.users.ms_users.domain.client.Client;
import com.transactionmgmt.users.ms_users.integration.rabbitmq.CommandPublisher;
import com.transactionmgmt.users.ms_users.integration.rabbitmq.command.CreateAccountCommand;
import com.transactionmgmt.users.ms_users.integration.rabbitmq.command.AccountType;
import com.transactionmgmt.users.ms_users.persistence.adapters.client.ClientRepository;
import com.transactionmgmt.users.ms_users.service.dto.ClientDto;
import com.transactionmgmt.users.ms_users.service.dto.CreateClientDto;
import com.transactionmgmt.users.ms_users.service.dto.UpdateClientDto;
import com.transactionmgmt.users.ms_users.service.mappers.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientDtoMapper mapper;
    private final CommandPublisher commandPublisher;

    @Override
    public void createClient(CreateClientDto dto) {
        Client newClient = mapper.toModel(dto);
        newClient.setStatus(true);
        Client savedClient = repository.saveClient(newClient);

        CreateAccountCommand event = new CreateAccountCommand(
                generateNumberAccount(),
                AccountType.AHORROS,
                0D,
                savedClient.getClientId()                
        );
        
        commandPublisher.sendCreateAccount(event);
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
        if (clienteRequestDTO.phoneNumber() != null) {
            clientToUpdate.setPhoneNumber(clienteRequestDTO.phoneNumber());
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

    @Override
    public ClientDto getClientById(Long clienteId) {
        Client client = repository.findByClientId(clienteId);
        return mapper.toResponse(client);
    }

    public long generateNumberAccount() {
        int minLength = 6;
        int maxLength = 9;
        int length = minLength + (int) (Math.random() * ((maxLength - minLength) + 1));
        long min = (long) Math.pow(10, length - 1);
        long max = (long) Math.pow(10, length) - 1;
        return min + (long) (Math.random() * (max - min + 1));
    }
}
