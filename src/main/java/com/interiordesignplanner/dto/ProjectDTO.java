package com.interiordesignplanner.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.interiordesignplanner.entity.ProjectStatus;

public record ProjectDTO(Long id, String clientName, String projectName, ProjectStatus status, Integer budget,
        LocalDate startDate,
        LocalDate dueDate,
        String description,
        String meetingURL,
        Instant completedAt) {
}