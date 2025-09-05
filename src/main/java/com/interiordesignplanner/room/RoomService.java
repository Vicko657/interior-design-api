package com.interiordesignplanner.room;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.interiordesignplanner.project.Project;
import com.interiordesignplanner.project.ProjectService;

@Service
public class RoomService {

    private final ProjectService projectService;
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository, ProjectService projectService) {
        this.roomRepository = roomRepository;
        this.projectService = projectService;

    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getType(String type) {

        RoomType[] typesValues = RoomType.values();

        for (RoomType type1 : typesValues) {
            if (type1.name().equalsIgnoreCase(type)) {
                return roomRepository.getByType(type1);
            }
        }

        return null;

    }

    public Room getRoom(Long id) throws NoSuchElementException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID" + id + "was not found"));
    }

    public Room getRoomEntity(Long id) throws NoSuchElementException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Transactional
    public Room addRoom(Room room, Long projectId) {
        if (room == null && projectId == null) {
            throw new IllegalArgumentException("Room must not be null");
        }

        if (room.getId() != null && roomRepository.existsById(room.getId())) {
            throw new OptimisticLockingFailureException("ID" + room.getId() + "was not found");
        }
        Project project = projectService.getProject(projectId);

        project.setRoom(room);
        room.setProject(project);
        projectService.saveProjectEntity(project);

        return room;

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
        roomRepository.deleteById(id);
        return room;
    }

    public Room reassignProject(Long projectId, Long roomId) {

        Room existingRoomId = getRoomEntity(roomId);
        Project project = projectService.getProject(projectId);
        project.setRoom(existingRoomId);
        existingRoomId.setProject(project);
        return roomRepository.save(existingRoomId);
    }

}
