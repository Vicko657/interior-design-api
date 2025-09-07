package com.interiordesignplanner.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.interiordesignplanner.client.ClientService;

/**
 * Unit tests for {@link ProjectService}.
 *
 * <p>
 * Validates project lifecycle management including creation,
 * updates and status transitions. Ensures relationships to clients
 * and rooms are correctly handled.
 * </p>
 * The tests use mocked service behavior.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Project Service Test Suite")
public class ProjectServiceTest {
    // Mock project repository
    @Mock
    public ProjectRepository pRepository;
    // Mock project service
    @InjectMocks
    public ProjectService pService;
    // Mock client service
    @InjectMocks
    public ClientService cService;

    public Project project1, project2;

    @BeforeEach
    // Created mock project tests
    public void setUp() {
        pService = new ProjectService(pRepository, cService);
        project1 = new Project("Industrial Loft Redesign", ProjectStatus.PLANNING, 20000,
                "Exposed brick walls, metal fixtures, and reclaimed wood accents",
                "https://meet.google.com/hyd-ken-csa",
                LocalDate.of(2025, 07, 20), LocalDate.of(2026, 01, 25));
        project2 = new Project("Luxury Master Bedroom", ProjectStatus.ON_HOLD, 5000,
                "Custom wardrobes, soft lighting, and premium fabrics for a hotel-like feel.",
                "https://meet.google.com/lhv-erf-oub", LocalDate.of(2025, 11, 10), LocalDate.of(2026, 5, 5));

    }

    /**
     * Tests for checking if Get all projects returns a empty list
     */
    @Test
    @DisplayName("GetAllProjects: Returns empty list")
    public void testGetAllProjects_ReturnsEmtyList() {
        // Arrange: Empty list is created and Mock Repository to test if it returns a
        // empty list
        List<Project> projects = Collections.emptyList();
        when(pRepository.findAll()).thenReturn(projects);

        // Act: Query the service layer the if a empty list is returned
        List<Project> result = pService.getAllProjects();

        // Assert: Verifies that the result empty
        assertThat(result).isEqualTo(projects);

    }

    /**
     * Tests for checking if Get all projects returns a list of projects
     */
    @Test
    @DisplayName("GetAllProjects: Returns all of the projects in the database")
    public void testGetAllProjects_ReturnsAllProjects() {
        // Arrange: A list created with projects and mock Repository to test if all
        // projects are returned
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        when(pRepository.findAll()).thenReturn(projects);

        // Act: Query the service layer the if all projects are returnes
        List<Project> result = pService.getAllProjects();

        // Assert: Verifies that the result is not null and projects are retrieved
        assertThat(result).isEqualTo(projects);
        assertThat(result).extracting(Project::getBudget).containsExactly(20000, 5000);
        verify(pRepository).findAll();
        verifyNoMoreInteractions(pRepository);

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(pRepository);
    }

}
