package com.interiordesignplanner.client;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ClientServiceTest {
    @Mock
    public ClientService cService;
    @Mock
    public ClientRepository cRepository;
    @Mock
    public Client client1, client2;

    @BeforeEach
    public void setUp() {
        cRepository = Mockito.mock(ClientRepository.class);
        cService = new ClientService(cRepository);
        client1 = new Client("FirstName1", "LastName1", "Email1", "PhoneNumber1",
                "Address1",
                "Notes1");
        client2 = new Client("FirstName2", "LastName2", "Email2", "PhoneNumber2", "Address2",
                "Notes2");

    }

    @Test
    @DisplayName("Returns empty list")
    public void testGetAllClientsIntiallyEmpty() {
        Mockito.when(cRepository.findAll()).thenReturn(List.of());
        assertTrue(cService.getAllClients().isEmpty());

    }

}
