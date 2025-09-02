package com.interiordesignplanner.room;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.interiordesignplanner.project.ProjectNotFoundException;

@RestController
public class RoomController {

    public RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/rooms/{projectId}")
    public Room addRoom(@RequestBody Room room, @PathVariable("projectId") Long projectId) {

        try {
            return roomService.addRoom(room, projectId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @PutMapping("/rooms/{roomId}")
    public Room updateRoom(@PathVariable("roomId") Long roomId, @RequestBody Room updateRoom) {

        try {
            return roomService.updateRoom(roomId, updateRoom);
        } catch (RoomNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping("/rooms/{roomId}/projects/{projectId}")
    public Room reassignProject(@PathVariable("roomId") Long roomId, @PathVariable("projectId") Long projectId) {

        try {
            return roomService.reassignProject(projectId, roomId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/rooms/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable("roomId") Long roomId) {
        try {
            roomService.deleteRoom(roomId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
