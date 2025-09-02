package com.interiordesignplanner.project;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class ProjectDTOMapper implements Function<Project, ProjectDTO> {

    @Override
    public ProjectDTO apply(Project project) {

        Long roomId = null;

        if (project.getRoom() != null) {
            roomId = project.getRoom().getId();
        } else {
            roomId = null;
        }

        return new ProjectDTO(
                project.getId(),
                project.getClient().getId(),
                project.getClient().getFirstName() + " " + project.getClient().getLastName(),
                project.getProjectName(),
                project.getProjectStatus(),
                project.getBudget(),
                project.getStartDate(),
                project.getDueDate(),
                project.getDescription(),
                project.getMeetingURL(),
                project.getCompletedAt(),
                roomId);
    }

}
