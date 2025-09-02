package com.interiordesignplanner.project;

import java.text.MessageFormat;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Long id) {
        super(MessageFormat.format("Project with {0} id was not found", id));

    }

}
