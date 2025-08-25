package com.transactionmgmt.users.ms_users.domain.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testBuilderAndGetters() {
        Client client = Client.builder()
                .clientId(1L)
                .password("clave123")
                .status(true)
                .name("Juan Perez")
                .gender(GenderType.Masculino)
                .age(30)
                .identification("1234567890")
                .address("Calle 123")
                .phoneNumber("0999999999")
                .build();

        assertEquals(1L, client.getClientId());
        assertEquals("clave123", client.getPassword());
        assertTrue(client.getStatus());
        assertEquals("Juan Perez", client.getName());
        assertEquals(GenderType.Masculino, client.getGender());
        assertEquals(30, client.getAge());
        assertEquals("1234567890", client.getIdentification());
        assertEquals("Calle 123", client.getAddress());
        assertEquals("0999999999", client.getPhoneNumber());
    }

    @Test
    void testSoftDelete() {
        Client client = Client.builder()
                .clientId(2L)
                .password("clave456")
                .status(true)
                .name("Maria Lopez")
                .gender(GenderType.Femenino)
                .age(25)
                .identification("0987654321")
                .address("Avenida 456")
                .phoneNumber("0888888888")
                .build();
        client.softDelete();
        assertFalse(client.getStatus());
    }

    @Test
    void testSetDefaultValues() {
        Client client = Client.builder()
                .clientId(3L)
                .password("clave789")
                .status(null)
                .name("Pedro Gomez")
                .gender(GenderType.Masculino)
                .age(40)
                .identification("1122334455")
                .address("Boulevard 789")
                .phoneNumber("0777777777")
                .build();
        client.setDefaultValues();
        assertTrue(client.getStatus());
    }
}

