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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class RoomController {

    public RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Retrieves all of the rooms", description = "Returns all the room specification, including the client and project it is linked to, roomType, roomSize, checkList of tasks, changes to the room")
    @GetMapping(value = "/rooms", produces = "application/json")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Adds a room to the project", description = "Creates a room with specifications for the project")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/rooms/{projectId}", produces = "application/json")
    public Room addRoom(@RequestBody Room room, @PathVariable("projectId") Long projectId) {

        try {
            return roomService.addRoom(room, projectId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Updates room", description = "Updates the room's specification")
    @PutMapping(value = "/rooms/{roomId}", produces = "application/json")
    public Room updateRoom(@PathVariable("roomId") Long roomId, @RequestBody Room updateRoom) {

        try {
            return roomService.updateRoom(roomId, updateRoom);
        } catch (RoomNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Reassigns room to a different project", description = "Updates to a different project for the room")
    @PatchMapping(value = "/rooms/{roomId}/projects/{projectId}", produces = "application/json")
    public Room reassignProject(@PathVariable("roomId") Long roomId, @PathVariable("projectId") Long projectId) {

        try {
            return roomService.reassignProject(projectId, roomId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Finds room by type", description = "Returns the same type of rooms")
    @GetMapping(value = "rooms/type/{type}", produces = "application/json")
    public List<Room> getType(@PathVariable("type") String type) {
        return roomService.getType(type);
    }

    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Deletes room", description = "Deletes the room and its specifications")
    @DeleteMapping(value = "/rooms/{roomId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable("roomId") Long roomId) {
        try {
            roomService.deleteRoom(roomId);
        } catch (ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
