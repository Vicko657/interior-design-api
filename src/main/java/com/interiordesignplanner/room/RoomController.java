package com.interiordesignplanner.room;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Rest Controller for managing rooms.
 * 
 * API endpoints to complete CRUD operations.
 */
@RestController
public class RoomController {

    // Room Service layer
    public RoomService roomService;

    // Constructor
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * GET: Returns Room with Id
     * 
     * @param id the room's unique identifier
     * @return room's entity
     * @response 200 if room was successfully found
     * @response 404 Not found is the room doesnt exist
     */
    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Finds room by ID", description = "Returns one room, including their roomType, roomSize, checkList of tasks, changes to the room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room with id was found"),
            @ApiResponse(responseCode = "404", description = "Room doesn't exist") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/rooms/{id}", produces = "application/json")
    public Room getRoom(@PathVariable Long id) {
        try {
            return roomService.getRoom(id);
        } catch (RoomNotFoundException e) {
            throw new RoomNotFoundException(e.getMessage());
        }

    }

    /**
     * GET: Returns all Rooms
     * 
     * @return all room entities on the system
     * @response 200 if all rooms are found
     */
    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Retrieves all of the rooms", description = "Returns all the room specification, including the client and project it is linked to, roomType, roomSize, checkList of tasks, changes to the room")
    @ApiResponse(responseCode = "200", description = "All rooms are found")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/rooms", produces = "application/json")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * POST: Adds a new Project to a Client
     * 
     * @param projectId the project's unique identifier
     * @param room      the room's object to be created
     * @return saved room for project with generated unique identifier
     * @response 201 if the room was successfully created
     * @response 404 bad request is input data is invalid
     */
    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Adds a room to the project", description = "Creates a room with specifications for the project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Room was created"),
            @ApiResponse(responseCode = "404", description = "Room columns have not been filled") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/rooms/{projectId}", produces = "application/json")
    public Room addRoom(@RequestBody Room room, @PathVariable("projectId") Long projectId) {

        try {
            return roomService.addRoom(room, projectId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    /**
     * PUT: Updates existing room details
     * 
     * @param roomId     the room's unique identifier
     * @param updateRoom the room's object to be updated
     * @return updated room entity
     * @response 201 if room was successfully updated
     * @response 404 not found is the room doesn't exist
     */
    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Updates room", description = "Updates the room's specification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room with id was updated"),
            @ApiResponse(responseCode = "404", description = "Room doesn't exist") })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/rooms/{roomId}", produces = "application/json")
    public Room updateRoom(@PathVariable("roomId") Long roomId, @RequestBody Room updateRoom) {

        try {
            return roomService.updateRoom(roomId, updateRoom);
        } catch (RoomNotFoundException e) {
            throw new RoomNotFoundException(e.getMessage());
        }
    }

    /**
     * PATCH: Reassigns room with a different project
     * 
     * @param projectId the project's unique identifier
     * @param roomId    the room's unique identifier
     * @return a new one to one relationship with
     * @response 200 if room is reassigned to a project
     * @response 404 if projectId or roomId is not found
     */
    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Reassigns room to a different project", description = "Updates to a different project for the room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room with id is reassigned"),
            @ApiResponse(responseCode = "404", description = "Room or Project doesn't exist") })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/rooms/{roomId}/projects/{projectId}", produces = "application/json")
    public Room reassignProject(@PathVariable("roomId") Long roomId, @PathVariable("projectId") Long projectId) {

        try {
            return roomService.reassignProject(projectId, roomId);
        } catch (RoomNotFoundException e) {
            throw new RoomNotFoundException(e.getMessage());
        }
    }

    /**
     * GET: Returns all Rooms with the same type
     * 
     * @return all rooms with the same specific type
     * @response 200 if all room's with the same type are returned
     * @response 404 if room type is not found
     */
    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Finds room by type", description = "Returns the same type of rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room type is found"),
            @ApiResponse(responseCode = "404", description = "Room type doesn't exist") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "rooms/type/{type}", produces = "application/json")
    public List<Room> getType(@PathVariable("type") String type) {
        try {
            return roomService.getRoomsByType(type);
        } catch (RoomNotFoundException e) {
            throw new RoomNotFoundException(e.getMessage());
        }
    }

    /**
     * DELETE: Deletes existing Room
     * 
     * @param roomId the room's unique identifier
     * @return deleted room entity off the system
     * @response 204 if room was successfully deleted
     * @response 404 not found is the room doesn't exist
     */
    @Tag(name = "rooms", description = "Project's Room specification")
    @Operation(summary = "Deletes room", description = "Deletes the room and its specifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Room with id was deleted"),
            @ApiResponse(responseCode = "404", description = "Room doesn't exist") })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/rooms/{roomId}", produces = "application/json")
    public void deleteProject(@PathVariable("roomId") Long roomId) {
        try {
            roomService.deleteRoom(roomId);
        } catch (RoomNotFoundException e) {
            throw new RoomNotFoundException(e.getMessage());
        }
    }

}
