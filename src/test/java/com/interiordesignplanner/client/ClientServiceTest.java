package com.interiordesignplanner.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
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
        // Arrange
        List<Client> clients = Collections.emptyList();
        Mockito.when(cRepository.findAll()).thenReturn(clients);

        // Act
        List<Client> result = cService.getAllClients();

        // Assert
        assertThat(result).isEqualTo(clients);

    }

    @Test
    @DisplayName("GetAllClients: Returns all of the clients in the database")
    public void testGetAllClientsAfterDetailsAreStored() {
        // Arrange
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        // Act
        Mockito.when(cRepository.findAll()).thenReturn(clients);
        List<Client> result = cService.getAllClients();

        // Assert
        assertThat(result).isEqualTo(clients);
        assertThat(result).extracting(Client::getFirstName).containsExactly("FirstName1", "FirstName2");
        Mockito.verify(cRepository).findAll();
        Mockito.verifyNoMoreInteractions(cRepository);

    }

    @Test
    @DisplayName("GetByLastNameIgnoreCase: Returns Client by LastName")
    public void testGetByLastNameIgnoreCase() {
        // Arrange
        String LastName = "lastname1";
        Mockito.when(cRepository.findByLastNameIgnoreCase(LastName)).thenReturn(List.of(client1));

        // Act
        List<Client> result = cService.getByLastNameIgnoreCase(LastName);

        // Assert
        assertThat(result).isEqualTo(List.of(client1));
        assertThat(result).extracting(Client::getPhone).containsExactly("PhoneNumber1");
    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        Mockito.reset(cRepository);
    }

}
