package com.interiordesignplanner.client;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName(value = "Client Service Test Suite")
public class ClientServiceTest {

    @Mock
    public ClientRepository cRepository;

    @InjectMocks
    public ClientService cService;

    public Client client1, client2;

    @BeforeEach
    public void setUp() {
        cService = new ClientService(cRepository);
        client1 = new Client("FirstName1", "LastName1", "Email1", "PhoneNumber1",
                "Address1",
                "Notes1");
        client2 = new Client("FirstName2", "LastName2", "Email2", "PhoneNumber2", "Address2",
                "Notes2");

    }

    @Test
    @DisplayName("GetAllClients: Returns empty list")
    public void testGetAllClientsIntiallyEmpty() {
        Mockito.when(cRepository.findAll()).thenReturn(List.of());
        assertTrue(cService.getAllClients().isEmpty());

    }

}
