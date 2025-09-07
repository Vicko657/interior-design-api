package com.interiordesignplanner.project;

import java.text.MessageFormat;

/**
 * Thrown when a project entity is not found in the interior design planner.
 */
public class ProjectNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProjectNotFoundException when the project entity with
     * the projectId is not found.
     *
     * @param id is not found
     */
    public ProjectNotFoundException(Long id) {
        super(MessageFormat.format("Project with {0} id was not found", id));

    }

}
