package com.interiordesignplanner.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.interiordesignplanner.dto.RoomDTO;
import com.interiordesignplanner.dto.RoomDTOMapper;
import com.interiordesignplanner.entity.Project;
import com.interiordesignplanner.entity.Room;
import com.interiordesignplanner.exception.RoomNotFoundException;
import com.interiordesignplanner.repository.RoomRepository;

@Service
public class RoomService {

    private final ProjectService projectService;
    private final RoomRepository roomRepository;
    private final RoomDTOMapper roomDTOMapper;

    public RoomService(RoomRepository roomRepository, RoomDTOMapper roomDTOMapper,
            ProjectService projectService) {
        this.roomRepository = roomRepository;
        this.roomDTOMapper = roomDTOMapper;
        this.projectService = projectService;

    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(roomDTOMapper)
                .collect(Collectors.toList());
    }

    public RoomDTO getRoom(Long id) throws NoSuchElementException {
        return roomRepository.findById(id)
                .map(roomDTOMapper)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    public Room getRoomEntity(Long id) throws NoSuchElementException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    public Room addRoom(Room room, Long projectId) {
        if (room == null) {
            throw new IllegalArgumentException("Room must not be null");
        }

        if (room.getId() != null && roomRepository.existsById(room.getId())) {
            throw new OptimisticLockingFailureException("ID" + room.getId() + "was not found");
        }
        Project project = projectService.getProjectEntity(projectId);

        room.setProject(project);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoomId = getRoomEntity(id);
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
        Room room = getRoomEntity(id);
        if (room.getProject() != null) {
            room.getProject().setRoom(null);
        }
        roomRepository.deleteById(id);
        return room;
    }

    public Room reassignProject(Long projectId, Long roomId) {

        Room existingRoomId = getRoomEntity(roomId);
        Project project = projectService.getProjectEntity(projectId);
        existingRoomId.setProject(project);
        return roomRepository.save(existingRoomId);
    }

}
