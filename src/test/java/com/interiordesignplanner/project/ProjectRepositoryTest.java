package com.interiordesignplanner.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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
 * Unit tests for {@link ProjectRepository}.
 *
 * <p>
 * This class verifies the persistence and retrieval of {@link Project}
 * entities.
 * It focuses on repository-level behavior including:
 * <p>
 * The tests use mocked repository behavior.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Project Repository Test Suite")
public class ProjectRepositoryTest {

    // Mock project repository
    @Mock
    public ProjectRepository pRepository;

    public Deadline dtest1;
    public Deadline dtest2;
    public Deadline dtest3;
    public Project ptest3;

    @BeforeEach
    public void setUp() {
        // Created mock project tests
        dtest1 = new Deadline(LocalDate.of(2026, 1, 25), LocalDate.of(2025, 07, 20), "Industrial Loft Redesign",
                ProjectStatus.ACTIVE, 10L, 18L);
        dtest2 = new Deadline(LocalDate.of(2026, 5, 5), LocalDate.of(2025, 11, 10), "Luxury Master Bedroom",
                ProjectStatus.PLANNING, 13L, 20L);
        dtest3 = new Deadline(LocalDate.of(2026, 1, 10), LocalDate.of(2025, 7,
                20),
                "Scandinavian Living Room",
                ProjectStatus.ON_HOLD, 13L, 21L);
        ptest3 = new Project("Luxury Master Bedroom", ProjectStatus.ACTIVE, 5000,
                "Custom wardrobes, soft lighting, and premium fabrics for a hotel-like feel.",
                "https://meet.google.com/lhv-erf-oub", LocalDate.of(2025, 11, 10), LocalDate.of(2026, 5, 5));
    }

    /**
     * Tests if the Project can be found by their status
     */
    @Test
    @DisplayName("GetProjectsByStatus: Finds project by status")
    public void testGetProjectsByStatus_ReturnsProjects() {

        // Arrange: Mock repository to return test for status (ACTIVE).

        when(pRepository.findProjectsByStatus(ProjectStatus.ACTIVE)).thenReturn(List.of(ptest3));

        // Act: Query the repository with status (ACTIVE)
        List<Project> result = pRepository.findProjectsByStatus(ProjectStatus.ACTIVE);

        // Assert: Verify that the result only returns one test and is (ACTIVE)
        assertNotNull(result);
        assertThat(1).isEqualTo(result.size());
        assertThat(ptest3).isEqualTo(result.get(0));
        verify(pRepository).findProjectsByStatus(ProjectStatus.ACTIVE);

    }

    /**
     * Tests when the Project status is invalid and is not found
     */
    @Test
    @DisplayName("GetProjectsByStatus: Project is not found with status")
    public void testGetProjectByStatus_ReturnsEmptyList() {

        // Arrange: Mock repository to test a different status (COMPLETED).
        when(pRepository.findProjectsByStatus(ProjectStatus.COMPLETED)).thenReturn(Collections.emptyList());

        // Act: Query the repository with status (COMPLETED)
        List<Project> result = pRepository.findProjectsByStatus(ProjectStatus.COMPLETED);

        // Assert: Verify that the result doesnt return test
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pRepository).findProjectsByStatus(ProjectStatus.COMPLETED);

    }

    /**
     * Tests if projects are returned in order of due date
     */
    @Test
    @DisplayName("findAllProjectsDue: Finds all projects with due date")
    public void testfindAllProjectsDue_ReturnsProjects() {

        // Arrange: Mock repository to test if all the projects return in order of due
        // date
        when(pRepository.getAllProjectsOrderByDueDate()).thenReturn(List.of(dtest3, dtest1, dtest2));

        // Act: Query the repository with the getAllProjectsOrderByDueDate method
        List<Deadline> result = pRepository.getAllProjectsOrderByDueDate();

        // Assert: Verify that the results return in order
        assertNotNull(result);
        assertThat(result.get(0).dueDate()).isEqualTo(LocalDate.of(2026, 1, 10));
        assertThat(result.get(1).dueDate()).isEqualTo(LocalDate.of(2026, 1, 25));
        assertThat(result.get(2).dueDate()).isEqualTo(LocalDate.of(2026, 5, 5));
        verify(pRepository).getAllProjectsOrderByDueDate();

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(pRepository);
    }

}
