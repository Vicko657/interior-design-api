package com.interiordesignplanner.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.interiordesignplanner.entity.Room;

@Service
public class RoomDTOMapper implements Function<Room, RoomDTO> {

    @Override
    public RoomDTO apply(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getProject().getId(),
                room.getProject().getClient().getFirstName() + " "
                        + room.getProject().getClient().getLastName(),
                room.getProject().getProjectName(),
                room.getType(),
                "length:" + " " + room.getLength() + room.getUnit() + ", " + "height:" + " " + room.getHeight() + ", "
                        + "width:" + " " + room.getWidth()
                        + room.getUnit(),
                room.getChecklist(),
                room.getChanges());
    }

}
