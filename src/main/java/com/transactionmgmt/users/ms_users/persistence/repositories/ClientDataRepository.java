package com.transactionmgmt.users.ms_users.persistence.repositories;

import com.transactionmgmt.users.ms_users.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ClientDataRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByClientId(Long clienteId);
}

