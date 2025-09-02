package com.interiordesignplanner.project;

import java.time.Instant;
import java.time.LocalDate;

public record ProjectDTO(Long id,
        Long clientId,
        String clientName,
        String projectName,
        ProjectStatus status,
        Integer budget,
        LocalDate startDate,
        LocalDate dueDate,
        String description,
        String meetingURL,
        Instant completedAt,
        Long roomId) {
}