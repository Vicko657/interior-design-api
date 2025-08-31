package com.interiordesignplanner.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.entity.Project;
import com.interiordesignplanner.entity.Room;
import com.interiordesignplanner.exception.RoomNotFoundException;
import com.interiordesignplanner.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ProjectService projectService;

    public RoomService(RoomRepository roomRepository, ProjectService projectService) {
        this.roomRepository = roomRepository;
        this.projectService = projectService;

    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoom(Long id) throws NoSuchElementException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    public Room addRoom(Room room, Long projectId) {
        if (room == null && projectId == null) {
            throw new IllegalArgumentException("Room and projectId must not be null");
        }

        if (room.getId() != null && roomRepository.existsById(room.getId())) {
            throw new OptimisticLockingFailureException("ID" + room.getId() + "was not found");
        }

        // Assiging Project Id to the project
        Project project = projectService.getProjectEntity(projectId);
        room.setProject(project);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoomId = getRoom(id);
        existingRoomId.setType(room.getType());
        existingRoomId.setHeight(room.getHeight());
        existingRoomId.setLength(room.getLength());
        existingRoomId.setWidth(room.getWidth());
        existingRoomId.setUnit(room.getUnit());
        existingRoomId.setChecklist(room.getChecklist());
        existingRoomId.setChanges(room.getChanges());
        return roomRepository.save(existingRoomId);
    }

    public Room deleteRoom(Long id) {
        Room room = getRoom(id);
        roomRepository.deleteById(id);
        return room;
    }

    // Sets the Project to the room
    public Room reassignProject(Long projectId, Long roomId) {
        Room existingRoomId = getRoom(roomId);
        Project project = projectService.getProjectEntity(projectId);
        existingRoomId.setProject(project);
        return roomRepository.save(existingRoomId);
    }

}
