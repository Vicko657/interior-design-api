package com.interiordesignplanner.client;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link ClientService}.
 *
 * <p>
 * Verifies client creation, retrieval, updating, and deletion logic.
 * Ensures that validation rules and exception handling (such as
 * {@code ClientNotFoundException}) work as expected.
 * <p>
 * The tests use mocked service behavior.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Client Service Test Suite")
public class ClientServiceTest {

    // Mock client repository
    @Mock
    public ClientRepository cRepository;

    // Mock client service
    @InjectMocks
    public ClientService cService;

    public Client client1, client2;

    @BeforeEach
    public void setUp() {
        // Created mock client tests
        cService = new ClientService(cRepository);
        client1 = new Client("Jessica", "Cook", "jessicacook@gmail.com", "07314708068",
                "33 Elm Street, London, N2R 652",
                "Prefers eco-friendly materials");
        client2 = new Client("Alex", "Price", "aprice@gmail.com", "07828096962", "249 The Grove, Reading, R84 J5N",
                "Needs child-friendly furniture");

    }

    /**
     * Tests for when the Client is not found, returns a empty set and throws a
     * ClientNotFoundException
     */
    @Test
    @DisplayName("GetClient: Client ID is not found")
    public void testGetClient_ReturnsNotFound() {
        // Arrange: Set the clientId and mock the repository
        Long clientId = 3L;
        when(cRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act: Queries if the exception is thrown
        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () -> {
            cService.getClient(clientId);
        });

        String errorMessage = "Client with id " + clientId + " was not found";
        // Assert: Verifies exception matches the thrown exception
        assertThat(exception.getMessage())
                .isEqualTo(String.join(":", null, errorMessage));
    }

    /**
     * Tests for creating a new Client successfully
     */
    @Test
    @DisplayName("CreateClient: Adds a new Client")
    public void testCreateClient_ReturnsCreated() {
        // Arrange: Mock Repository to test if a new client has been created
        Client client3 = new Client("FirstName3", "LastName3", "Email3", "PhoneNumber3", "Address3",
                "Notes3");

        when(cRepository.save(any(Client.class))).thenReturn(client3);

        // Act: Query the service layer the if client is there
        Client result = cService.createClient(client3);

        // Assert: Verifies that the result is not null and client has been created
        assertNotNull(result);
        assertThat(result).extracting(Client::getFirstName).isEqualTo("FirstName3");
        verify(cRepository, times(1)).save(client3);

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(cRepository);
    }

}
