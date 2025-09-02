package com.interiordesignplanner.room;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.interiordesignplanner.project.Project;

@Service
public class RoomDTOMapper implements Function<Room, RoomDTO> {

        @Override
        public RoomDTO apply(Room room) {

                Project project = room.getProject();
                Long projectId = null;

                if (project != null) {
                        projectId = room.getProject().getId();
                } else {
                        projectId = null;
                }

                String clientName = null;
                String projectName = null;

                if (project != null) {
                        projectName = room.getProject().getProjectName();
                }

                if (project != null && project.getClient() != null) {
                        clientName = room.getProject().getClient().getFirstName() + " "
                                        + room.getProject().getClient().getLastName();
                }

                return new RoomDTO(
                                room.getId(),
                                projectId,
                                clientName,
                                projectName,
                                room.getType(),
                                "length:" + " " + room.getLength() + room.getUnit() + ", " + "height:" + " "
                                                + room.getHeight() + ", "
                                                + "width:" + " " + room.getWidth()
                                                + room.getUnit(),
                                room.getChecklist(),
                                room.getChanges());
        }

}
