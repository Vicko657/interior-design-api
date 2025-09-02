package com.interiordesignplanner.room;

public record RoomDTO(Long id,
                Long projectId,
                String clientName,
                String projectName,
                String roomType,
                String roomSize,
                String checklist,
                String changes) {

}
