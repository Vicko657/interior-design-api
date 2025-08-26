package com.interiordesignplanner.exception;

import java.text.MessageFormat;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(Long id) {
        super(MessageFormat.format("Rooom with {0} id was not found", id));

    }

}
