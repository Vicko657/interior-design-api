package com.interiordesignplanner.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.interiordesignplanner.entity.Project;

@Service
public class ProjectDTOMapper implements Function<Project, ProjectDTO> {

    @Override
    public ProjectDTO apply(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getClient().getFirstName() + " " + project.getClient().getLastName(),
                project.getProjectName(),
                project.getProjectStatus(),
                project.getBudget(),
                project.getStartDate(),
                project.getDueDate(),
                project.getDescription(),
                project.getMeetingURL(),
                project.getCompletedAt());
    }

}
