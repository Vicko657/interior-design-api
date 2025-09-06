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

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Project Service Test Suite")
public class ProjectServiceTest {

    @Mock
    public ProjectRepository pRepository;

    @InjectMocks
    public ProjectService pService;

    @InjectMocks
    public ClientService cService;

    public Project project1, project2;
    public Status status1, status2;

    @BeforeEach
    public void setUp() {
        pService = new ProjectService(pRepository, cService);
        project1 = new Project("Industrial Loft Redesign", 20000,
                "Exposed brick walls, metal fixtures, and reclaimed wood accents",
                "https://meet.google.com/hyd-ken-csa",
                LocalDate.of(2025, 07, 20), LocalDate.of(2026, 01, 25));
        project2 = new Project("Luxury Master Bedroom", 5000,
                "Custom wardrobes, soft lighting, and premium fabrics for a hotel-like feel.",
                "https://meet.google.com/lhv-erf-oub", LocalDate.of(2025, 11, 10), LocalDate.of(2026, 5, 5));

        status1 = new Status(ProjectStatus.ACTIVE, "Scandinavian Living Room", "Susan", "Vane",
                LocalDate.of(2025, 7, 20), LocalDate.of(2026, 1, 10), 9000, "https://meet.google.com/mno-pqr-stu", null,
                30L);
        status2 = new Status(ProjectStatus.ACTIVE, "Luxury Master Bedroom", "Jessica", "Cook",
                LocalDate.of(2025, 11, 10), LocalDate.of(2026, 5, 5), 5000,
                "https://meet.google.com/lhv-erf-oub", null, 25L);

    }

    @Test
    @DisplayName("GetAllProjects: Returns empty list")
    public void testGetAllProjectsIntiallyEmpty() {
        // Arrange
        List<Project> projects = Collections.emptyList();
        when(pRepository.findAll()).thenReturn(projects);

        // Act
        List<Project> result = pService.getAllProjects();

        // Assert
        assertThat(result).isEqualTo(projects);

    }

    @Test
    @DisplayName("GetAllProjects: Returns all of the projects in the database")
    public void testGetAllClientsAfterDetailsAreStored() {
        // Arrange
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);

        // Act
        when(pRepository.findAll()).thenReturn(projects);
        List<Project> result = pService.getAllProjects();

        // Assert
        assertThat(result).isEqualTo(projects);
        assertThat(result).extracting(Project::getBudget).containsExactly(20000, 5000);
        verify(pRepository).findAll();
        verifyNoMoreInteractions(pRepository);

    }

    @Test
    @DisplayName("GetByStatusIgnoreCase: Returns projects with the same status")
    public void testGetByStatusIgnoreCase() {

        // Arrange
        when(pRepository.getByStatus(ProjectStatus.ACTIVE)).thenReturn(List.of(status1, status2));

        // Act
        List<Status> result = pService.getProjectStatus("active");

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).extracting(Status::projectName).containsExactly("Scandinavian Living Room",
                "Luxury Master Bedroom");
        verify(pRepository).getByStatus(ProjectStatus.ACTIVE);
    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(pRepository);
    }

}
