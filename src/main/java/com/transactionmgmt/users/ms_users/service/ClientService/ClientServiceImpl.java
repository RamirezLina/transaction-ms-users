package com.transactionmgmt.users.ms_users.service.ClientService;

import com.transactionmgmt.users.ms_users.domain.client.Client;
import com.transactionmgmt.users.ms_users.integration.rabbitmq.CommandPublisher;
import com.transactionmgmt.users.ms_users.integration.rabbitmq.command.CreateAccountCommand;
import com.transactionmgmt.users.ms_users.integration.rabbitmq.command.AccountType;
import com.transactionmgmt.users.ms_users.persistence.adapters.client.ClientRepository;
import com.transactionmgmt.users.ms_users.service.dto.ClientDto;
import com.transactionmgmt.users.ms_users.service.dto.CreateClientDto;
import com.transactionmgmt.users.ms_users.service.dto.UpdateClientDto;
import com.transactionmgmt.users.ms_users.service.exception.BusinessException;
import com.transactionmgmt.users.ms_users.service.mappers.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
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
        String hashedPassword = BCrypt.hashpw(newClient.getPassword(), BCrypt.gensalt());
        newClient = Client.builder()
                .clientId(newClient.getClientId())
                .name(newClient.getName())
                .gender(newClient.getGender())
                .age(newClient.getAge())
                .identification(newClient.getIdentification())
                .address(newClient.getAddress())
                .phoneNumber(newClient.getPhoneNumber())
                .password(hashedPassword)
                .status(true)
                .build();
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
        Client clientToUpdate = repository.findByClientId(clienteId)
                .orElseThrow(BusinessException.Type.CLIENT_NOT_EXISTS::build);

        String password = clientToUpdate.getPassword();
        // Si el DTO tiene un campo para password y es diferente, encriptar
        // (asumiendo que UpdateClientDto tiene un campo password, si no, omitir este bloque)
        // password = BCrypt.hashpw(clienteRequestDTO.password(), BCrypt.gensalt());

        Client updatedClient = Client.builder()
                .clientId(clientToUpdate.getClientId())
                .password(password)
                .status(clientToUpdate.getStatus())
                .name(clienteRequestDTO.name())
                .gender(clienteRequestDTO.gender())
                .age(clienteRequestDTO.age())
                .identification(clienteRequestDTO.identification())
                .address(clienteRequestDTO.address())
                .phoneNumber(clienteRequestDTO.phoneNumber())
                .build();

        repository.saveClient(updatedClient);
        return mapper.toResponse(updatedClient);
    }

    @Override
    public void deleteClient(Long clienteId) {
        Client clientToDelete = repository.findByClientId(clienteId)
                .orElseThrow(BusinessException.Type.CLIENT_NOT_EXISTS::build);
        clientToDelete.softDelete();
        repository.saveClient(clientToDelete);
        
    }

    @Override
    public ClientDto getClientById(Long clienteId) {
        Client client = repository.findByClientId(clienteId)
                .orElseThrow(BusinessException.Type.CLIENT_NOT_EXISTS::build);
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
