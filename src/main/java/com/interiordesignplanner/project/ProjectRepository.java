package com.interiordesignplanner.project;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, Long> {

    @Query("select i.status, i.projectName, i.client.firstName, i.client.lastName, i.startDate, i.dueDate, i.room.id from Project i where i.status = :status")
    List<Status> getByStatus(@Param("status") ProjectStatus status);

    @Query("select p.dueDate, p.startDate, p.projectName, p.status, p.client.id, p.room.id from Project p order by p.dueDate ASC")
    List<Deadline> findAllProjectsDueSoonOrderByDueDateAsc();
}

// Projection Queries
record Status(ProjectStatus status, String projectName, String firstName, String lastName, LocalDate startDate,
        LocalDate dueDate, Long roomId) {
}

record Deadline(LocalDate dueDate, LocalDate startDate, String projectName, ProjectStatus status, Long clientId, Long roomId) {

}
