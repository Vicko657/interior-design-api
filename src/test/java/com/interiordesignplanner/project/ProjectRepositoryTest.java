package com.interiordesignplanner.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Project Repository Test Suite")
public class ProjectRepositoryTest {

    @Mock
    public ProjectRepository pRepository;

    public Project ptest1;
    public Project ptest2;
    public Status stest3;

    @BeforeEach
    public void setUp() {
        ptest1 = new Project("Industrial Loft Redesign", 20000,
                "Exposed brick walls, metal fixtures, and reclaimed wood accents",
                "https://meet.google.com/hyd-ken-csa",
                LocalDate.of(2025, 07, 20), LocalDate.of(2026, 01, 25));
        ptest2 = new Project("Luxury Master Bedroom", 5000,
                "Custom wardrobes, soft lighting, and premium fabrics for a hotel-like feel.",
                "https://meet.google.com/lhv-erf-oub", LocalDate.of(2025, 11, 10), LocalDate.of(2026, 5, 5));
        stest3 = new Status(ProjectStatus.ACTIVE, "Scandinavian Living Room", "Susan", "Vane",
                LocalDate.of(2025, 7, 20), LocalDate.of(2026, 1, 10), 9000, "https://meet.google.com/mno-pqr-stu", null,
                30L);
    }

    @Test
    @DisplayName("GetByStatus: Finds project by status")
    public void testGetByStatus_ReturnsProjects() {

        // Arrange
        Mockito.when(pRepository.getByStatus(ProjectStatus.ACTIVE)).thenReturn(List.of(stest3));

        // Act
        List<Status> result = pRepository.getByStatus(ProjectStatus.ACTIVE);

        // Assert
        assertNotNull(result);
        assertThat(1).isEqualTo(result.size());
        assertThat(stest3).isEqualTo(result.get(0));
        Mockito.verify(pRepository).getByStatus(ProjectStatus.ACTIVE);

    }

    @Test
    @DisplayName("GetByStatus: Project is not found with status")
    public void testGetByStatus_ReturnsEmptyList() {

        // Arrange
        Mockito.when(pRepository.getByStatus(ProjectStatus.COMPLETED)).thenReturn(Collections.emptyList());

        // Act
        List<Status> result = pRepository.getByStatus(ProjectStatus.COMPLETED);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(pRepository).getByStatus(ProjectStatus.COMPLETED);

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        Mockito.reset(pRepository);
    }

}
