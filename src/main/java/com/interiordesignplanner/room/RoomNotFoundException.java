package com.interiordesignplanner.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a room entity is not found in the interior design planner.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends RuntimeException {

    /**
     * Constructs a new RoomNotFoundException when the room entity with
     * the roomId is not found.
     *
     * @param id is not found
     */
    private final String message;

    public RoomNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.join(":", super.getMessage(), this.message);
    }

}
