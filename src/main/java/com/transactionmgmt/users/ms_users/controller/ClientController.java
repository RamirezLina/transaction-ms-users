package com.transactionmgmt.users.ms_users.controller;

import com.transactionmgmt.users.ms_users.service.ClientService.ClientService;
import com.transactionmgmt.users.ms_users.service.dto.ClientDto;
import com.transactionmgmt.users.ms_users.service.dto.CreateClientDto;
import com.transactionmgmt.users.ms_users.service.dto.UpdateClientDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Void> saveClient(@Valid  @RequestBody CreateClientDto clientDto) {
        clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clientService.getClientById(clienteId));
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClientDto> actualizarCliente(@PathVariable Long clienteId, @Valid @RequestBody UpdateClientDto clientDto) {
        return ResponseEntity.ok(clientService.updateClient(clienteId, clientDto));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long clienteId) {
        clientService.deleteClient(clienteId);
        return ResponseEntity.noContent().build();
    }
}
