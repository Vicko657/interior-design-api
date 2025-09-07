package com.interiordesignplanner.room;

import java.text.MessageFormat;

/**
 * Thrown when a room entity is not found in the interior design planner.
 */
public class RoomNotFoundException extends RuntimeException {

    /**
     * Constructs a new RoomNotFoundException when the room entity with
     * the roomId is not found.
     *
     * @param id is not found
     */
    public RoomNotFoundException(Long id) {
        super(MessageFormat.format("Rooom with {0} id was not found", id));

    }

}
