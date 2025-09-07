package com.interiordesignplanner.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link ClientRepository}.
 *
 * <p>
 * This class verifies the persistence and retrieval of {@link Client} entities.
 * It focuses on repository-level behavior including:
 * <p>
 * The tests use mocked repository behavior.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Client Repository Test Suite")
public class ClientRepositoryTest {

    // Mock client repository
    @Mock
    public ClientRepository cRepository;

    public Client ctest1;
    public Client ctest2;
    public Client ctest3;

    @BeforeEach
    public void setUp() {
        // Created mock client tests
        ctest1 = new Client("Jessica", "Cook", "jessicacook@gmail.com", "07314708068", "33 Elm Street, London, N2R 652",
                "Prefers eco-friendly materials");
        ctest2 = new Client("Alex", "Price", "aprice@gmail.com", "07828096962", "249 The Grove, Reading, R84 J5N",
                "Needs child-friendly furniture");
        ctest3 = new Client("Simon", "Harris", "harrissimon@gmail.com", "07855443322",
                "89 Riverbank Road, Birmingham, B23 O92",
                "Loves minimalist design");
    }

    /**
     * Tests if the Client can be found with their lastname and different case
     */
    @Test
    @DisplayName("FindByLastName: Finds client by lastname and ignorescase")
    public void testfindByLastNameIgnoreCase_ReturnsSameClient() {

        // Arrange: Prepare test clients mapped to last names in different cases
        Map<String, List<Client>> test = new HashMap<>();
        test.put("cook", List.of(ctest1));
        test.put("PRICE", List.of(ctest2));
        test.put("Harr", List.of(ctest3));

        for (Map.Entry<String, List<Client>> entry : test.entrySet()) {

            String lastName = entry.getKey();
            List<Client> expectedClients = entry.getValue();

            // Mock repository behavior
            when(cRepository.findByLastNameIgnoreCase(lastName)).thenReturn(expectedClients);

            // Act: Query repository with each last name
            List<Client> result = cRepository.findByLastNameIgnoreCase(lastName);

            // Assert: Verify results match expected clients, ignoring case
            assertNotNull(result);
            assertEquals(expectedClients.size(), result.size());
            assertEquals(expectedClients, result);

        }

    }

    /**
     * Tests when the Client isnt found by LastName and returns a empty set
     */
    @Test
    @DisplayName("FindByLastName: Client isnt found by lastname and ignorescase")
    public void testfindByLastNameIgnoreCase_ReturnsEmptyList() {

        // Arrange: Mock Repository to test a different if the client with last name
        // ("Brown") is found
        when(cRepository.findByLastNameIgnoreCase("Brown")).thenReturn(Collections.emptyList());

        // Act: Query the repository the lastname ("Brown")
        List<Client> result = cRepository.findByLastNameIgnoreCase("Brown");

        // Assert: result is not null, is empty and repository was called
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(cRepository).findByLastNameIgnoreCase("Brown");

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        Mockito.reset(cRepository);
    }

}