package com.interiordesignplanner.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.interiordesignplanner.entity.Project;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, Long> {

}
