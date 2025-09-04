package com.interiordesignplanner.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, Long> {

    @Query("SELECT i FROM Project i WHERE i.status = :status")
    List<Project> getByStatus(@Param("status") ProjectStatus status);

}
