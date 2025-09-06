package com.interiordesignplanner.client;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        when(cRepository.findAll()).thenReturn(clients);

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
        when(cRepository.findAll()).thenReturn(clients);
        List<Client> result = cService.getAllClients();

        // Assert
        assertThat(result).isEqualTo(clients);
        assertThat(result).extracting(Client::getFirstName).containsExactly("FirstName1", "FirstName2");
        verify(cRepository).findAll();
        verifyNoMoreInteractions(cRepository);

    }

    @Test
    @DisplayName("GetByLastNameIgnoreCase: Returns client by lastname")
    public void testGetByLastNameIgnoreCase() {
        // Arrange
        String lastName = "lastname1";
        when(cRepository.findByLastNameIgnoreCase(lastName)).thenReturn(List.of(client1));

        // Act
        List<Client> result = cService.getByLastNameIgnoreCase(lastName);

        // Assert
        assertThat(result).isEqualTo(List.of(client1));
        assertThat(result).extracting(Client::getPhone).containsExactly("PhoneNumber1");
    }

    @Test
    @DisplayName("GetByLastNameIgnoreCase: Client not found")
    public void testGetByLastNameIgnoreCase_NotFound() {
        // Arrange
        String lastName = "lastName4";
        when(cRepository.findByLastNameIgnoreCase(lastName)).thenReturn(Collections.emptyList());

        // Act
        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () -> {
            cService.getByLastNameIgnoreCase(lastName);
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("No clients found with the lastname " + lastName);
    }

    @Test
    @DisplayName("GetClient: Returns client by ID")
    public void testGetClient() {
        // Arrange
        Long clientId = 3L;
        when(cRepository.findById(clientId)).thenReturn(Optional.of(client2));

        // Act
        Client result = cService.getClient(clientId);

        // Assert
        assertThat(result).isEqualTo(client2);
        assertThat(result).extracting(Client::getFirstName).isEqualTo("FirstName2");
    }

    @Test
    @DisplayName("GetClient: Client ID is not found")
    public void testGetClient_NotFound() {
        // Arrange
        Long clientId = 3L;
        when(cRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act
        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () -> {
            cService.getClient(clientId);
        });

        // Assert
        assertThat(exception.getMessage())
                .isEqualTo(MessageFormat.format("Client with {0} id was not found", clientId));
    }

    @Test
    @DisplayName("CreateClient: Adds a new Client")
    public void testCreateClient() {
        // Arrange
        Client client3 = new Client("FirstName3", "LastName3", "Email3", "PhoneNumber3", "Address3",
                "Notes3");

        when(cRepository.save(any(Client.class))).thenReturn(client3);

        // Act
        Client result = cService.createClient(client3);
        assertNotNull(result);

        // Assert
        assertThat(result).extracting(Client::getFirstName).isEqualTo("FirstName3");
        verify(cRepository, times(1)).save(client3);

    }

    @Test
    @DisplayName("UpdateClient: Updates Client Details")
    public void testUpdateClient() {
        // Arrange

        Long clientId = 1L;
        Client updatedClient = new Client("Josh", "Crow", "Email1", "PhoneNumber1",
                "Address1",
                "Notes1");
        when(cRepository.findById(clientId)).thenReturn(Optional.of(client1));
        when(cRepository.save(client1)).thenReturn(client1);

        // Act
        Client result = cService.updateClient(clientId, updatedClient);
        assertNotNull(result);

        // Assert
        assertThat(result).extracting(Client::getFirstName).isEqualTo("Josh");
        verify(cRepository, times(1)).save(client1);

    }

    @Test
    @DisplayName("DeleteClient: Deletes client details")
    public void testDeleteClient() {
        // Arrange
        Long clientId = 1L;
        when(cRepository.existsById(clientId)).thenReturn(true);

        // Act
        cService.deleteClient(clientId);

        // Assert
        verify(cRepository).existsById(clientId);
        verify(cRepository).deleteById(clientId);

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(cRepository);
    }

}
