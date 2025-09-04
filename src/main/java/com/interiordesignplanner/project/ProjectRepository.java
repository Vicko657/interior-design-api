package com.interiordesignplanner.project;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, Long> {

    @Query("select i.status, i.projectName, i.client.firstName, i.client.lastName, i.startDate, i.dueDate from Project i where i.status = :status")
    List<Status> getByStatus(@Param("status") ProjectStatus status);

    @Query("SELECT p FROM Project p ORDER BY p.dueDate ASC")
    List<Project> findAllProjectsOrderByDueDateAsc();
}

// Projection Queries
record Status(ProjectStatus status, String projectName, String firstName, String lastName, LocalDate startDate,
        LocalDate dueDate) {
}
