package com.interiordesignplanner.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link RoomRepository}.
 *
 * <p>
 * This class verifies the persistence and retrieval of {@link Room} entities.
 * It focuses on repository-level behavior including:
 * <p>
 * The tests use mocked repository behavior.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Room Repository Test Suite")
public class RoomRepositoryTest {

    // Mock room repository
    @Mock
    public RoomRepository rRepository;

    public Room rtest1;

    @BeforeEach
    public void setUp() {
        rtest1 = new Room(RoomType.BEDROOM, 4.5, 6.7, 4.0, "m", "Install lighting fixtures",
                "Changed wall color from white to light gray");
    }

    /**
     * Tests if the Room can be found by their type
     */
    @Test
    @DisplayName("GetRoomsByType: Finds rooms by type")
    public void testGetRoomsByType_ReturnsProjects() {

        // Arrange: Mock repository to return test for type (BEDROOM).
        when(rRepository.findRoomsByType(RoomType.BEDROOM)).thenReturn(List.of(rtest1));

        // Act: Query the repository with type (BEDROOM)
        List<Room> result = rRepository.findRoomsByType(RoomType.BEDROOM);

        // Assert: Verify that the result does returns one test and is (BEDROOM)
        assertNotNull(result);
        assertThat(1).isEqualTo(result.size());
        assertThat(rtest1).isEqualTo(result.get(0));
        verify(rRepository).findRoomsByType(RoomType.BEDROOM);

    }

    /**
     * Tests when the Room type is invalid and is not found
     */
    @Test
    @DisplayName("GetRoomsByType: Room is not found with type")
    public void testGetByStatus_ReturnsEmptyList() {

        // Arrange: Mock repository to test a different type (DINING_ROOM).
        when(rRepository.findRoomsByType(RoomType.DINING_ROOM)).thenReturn(Collections.emptyList());

        // Act: Query the repository with type (BEDROOM)
        List<Room> result = rRepository.findRoomsByType(RoomType.DINING_ROOM);

        // Assert: Verify that the result doesnt return test
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(rRepository).findRoomsByType(RoomType.DINING_ROOM);

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(rRepository);
    }

}
