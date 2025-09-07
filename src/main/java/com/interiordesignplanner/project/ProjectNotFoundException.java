package com.interiordesignplanner.project;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a project entity is not found in the interior design planner.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProjectNotFoundException when the project entity with
     * the projectId is not found.
     *
     */
    private final String message;

    public ProjectNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.join(":", super.getMessage(), this.message);
    }

}
