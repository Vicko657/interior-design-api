package com.interiordesignplanner.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Client Repository Test Suite")
public class ClientRepositoryTest {

    @Mock
    public ClientRepository clientRepository;

    public Client ctest1;
    public Client ctest2;
    public Client ctest3;

    @BeforeEach
    public void setUp() {
        ctest1 = new Client("Jessica", "Cook", "jessicacook@gmail.com", "07314708068", "33 Elm Street, London, N2R 652",
                "Prefers eco-friendly materials");
        ctest2 = new Client("Alex", "Price", "aprice@gmail.com", "07828096962", "249 The Grove, Reading, R84 J5N",
                "Needs child-friendly furniture");
        ctest3 = new Client("Simon", "Harris", "harrissimon@gmail.com", "07855443322",
                "89 Riverbank Road, Birmingham, B23 O92",
                "Loves minimalist design");
    }

    @Test
    @DisplayName("finds client by lastname and ignorescase")
    public void testfindByLastNameIgnoreCase_ReturnsSameClient() {

        // Arrange
        List<Client> expectedClients = Arrays.asList(ctest1);
        when(clientRepository.findByLastNameIgnoreCase("cook")).thenReturn(expectedClients);

        // Act
        List<Client> result = clientRepository.findByLastNameIgnoreCase("cook");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ctest1, result.get(0));
        verify(clientRepository).findByLastNameIgnoreCase("cook");

    }

}
