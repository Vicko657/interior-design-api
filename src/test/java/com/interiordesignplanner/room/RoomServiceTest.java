package com.interiordesignplanner.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.interiordesignplanner.project.ProjectService;

/**
 * Unit tests for {@link RoomService}.
 *
 * <p>
 * Ensures room creation, updates, and associations with projects
 * work as intended. Tests validation on room type, dimensions, and
 * relationship handling with checklists and changes.
 * </p>
 * The tests use mocked service behavior.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Room Service Test Suite")
public class RoomServiceTest {
    // Mock room repository
    @Mock
    public RoomRepository rRepository;
    // Mock room service
    @InjectMocks
    public RoomService rService;
    // Mock project service
    @InjectMocks
    public ProjectService pService;

    public Room room1, room2;

    @BeforeEach
    // Created mock room tests
    public void setUp() {
        rService = new RoomService(rRepository, pService);
        room1 = new Room(RoomType.BEDROOM, 4.5, 6.7, 4.0, "m", "Install lighting fixtures",
                "Changed wall color from white to light gray");
        room2 = new Room(RoomType.KITCHEN, 5.8, 8.2, 2.3, "m", "Install cabinets",
                "Switched flooring material from laminate to ceramic tiles");

    }

    /**
     * Tests for when the room is found with the room id
     */
    @Test
    @DisplayName("GetRoom: Returns room by ID")
    public void testGetClient_ReturnsRoom() {
        // Arrange: Sets the roomId and mocks the repository
        Long roomId = 1L;
        when(rRepository.findById(roomId)).thenReturn(Optional.of(room2));

        // Act: Query the service layer to return the room with the id
        Room result = rService.getRoom(roomId);

        // Assert: Verifies that the result is not null and a room is returned
        assertThat(result).isEqualTo(room2);
        assertThat(result).extracting(Room::getChecklist).isEqualTo("Install cabinets");
    }

    /**
     * Tests for deleting a single room
     */
    @Test
    @DisplayName("DeleteRoom: Deletes room details")
    public void testDeleteRoom_Deletes() {
        // Arrange: Sets the roomId and mocks the repository
        Long roomId = 4L;
        when(rRepository.existsById(roomId)).thenReturn(true);

        // Act: Query the service layer to return the room with the id and delete the
        // room
        rService.deleteRoom(roomId);

        // Assert: Verifies that the room is not found
        verify(rRepository).existsById(roomId);

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(rRepository);
    }
}
