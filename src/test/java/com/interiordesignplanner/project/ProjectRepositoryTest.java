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

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Project Repository Test Suite")
public class ProjectRepositoryTest {

    @Mock
    public ProjectRepository pRepository;

    public Deadline dtest1;
    public Deadline dtest2;
    public Deadline dtest3;
    public Status stest3;

    @BeforeEach
    public void setUp() {
        dtest1 = new Deadline(LocalDate.of(2026, 1, 25), LocalDate.of(2025, 07, 20), "Industrial Loft Redesign",
                ProjectStatus.ACTIVE, 10L, 18L);
        dtest2 = new Deadline(LocalDate.of(2026, 5, 5), LocalDate.of(2025, 11, 10), "Luxury Master Bedroom",
                ProjectStatus.PLANNING, 13L, 20L);
        dtest3 = new Deadline(LocalDate.of(2026, 1, 10), LocalDate.of(2025, 7,
                20),
                "Scandinavian Living Room",
                ProjectStatus.ON_HOLD, 13L, 21L);
        stest3 = new Status(ProjectStatus.ACTIVE, "Scandinavian Living Room", "Susan", "Vane",
                LocalDate.of(2025, 7, 20), LocalDate.of(2026, 1, 10), 9000, "https://meet.google.com/mno-pqr-stu", null,
                30L);
    }

    @Test
    @DisplayName("GetByStatus: Finds project by status")
    public void testGetByStatus_ReturnsProjects() {

        // Arrange
        when(pRepository.getByStatus(ProjectStatus.ACTIVE)).thenReturn(List.of(stest3));

        // Act
        List<Status> result = pRepository.getByStatus(ProjectStatus.ACTIVE);

        // Assert
        assertNotNull(result);
        assertThat(1).isEqualTo(result.size());
        assertThat(stest3).isEqualTo(result.get(0));
        verify(pRepository).getByStatus(ProjectStatus.ACTIVE);

    }

    @Test
    @DisplayName("GetByStatus: Project is not found with status")
    public void testGetByStatus_ReturnsEmptyList() {

        // Arrange
        when(pRepository.getByStatus(ProjectStatus.COMPLETED)).thenReturn(Collections.emptyList());

        // Act
        List<Status> result = pRepository.getByStatus(ProjectStatus.COMPLETED);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pRepository).getByStatus(ProjectStatus.COMPLETED);

    }

    @Test
    @DisplayName("findAllProjectsDue: Finds all projects with due date")
    public void testfindAllProjectsDue_ReturnsProjects() {

        // Arrange
        when(pRepository.findAllProjectsDueSoonOrderByDueDateAsc()).thenReturn(List.of(dtest3, dtest1, dtest2));

        // Act
        List<Deadline> result = pRepository.findAllProjectsDueSoonOrderByDueDateAsc();

        // Assert
        assertNotNull(result);
        assertThat(result.get(0).dueDate()).isEqualTo(LocalDate.of(2026, 1, 10));
        assertThat(result.get(1).dueDate()).isEqualTo(LocalDate.of(2026, 1, 25));
        assertThat(result.get(2).dueDate()).isEqualTo(LocalDate.of(2026, 5, 5));
        verify(pRepository).findAllProjectsDueSoonOrderByDueDateAsc();

    }

    // Reset all mock objects
    @AfterEach
    public void tearDown() {
        reset(pRepository);
    }

}
