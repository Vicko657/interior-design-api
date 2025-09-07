package com.interiordesignplanner.client;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a client entity is not found in the interior design planner.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends NoSuchElementException {

    /**
     * Constructs a new ClientNotFoundException when the client entity with
     * the clientId and lastName is not found.
     */

    private final String message;

    public ClientNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.join(":", super.getMessage(), this.message);
    }

}
