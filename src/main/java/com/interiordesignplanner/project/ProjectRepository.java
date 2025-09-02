package com.interiordesignplanner.project;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, Long> {

}
