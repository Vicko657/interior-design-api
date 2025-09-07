package com.interiordesignplanner.client;

import java.text.MessageFormat;

/**
 * Thrown when a client entity is not found in the interior design planner.
 */
public class ClientNotFoundException extends RuntimeException {

    /**
     * Constructs a new ClientNotFoundException when the client entity with
     * the clientId is not found.
     *
     * @param id is not found
     */
    public ClientNotFoundException(Long id) {
        super(MessageFormat.format("Client with {0} id was not found", id));

    }

    /**
     * Constructs a new ClientNotFoundException when the client entity with the
     * lastname is not found.
     *
     * @param lastName is not found
     */
    public ClientNotFoundException(String lastName) {
        super(("No clients found with the lastname " + lastName));

    }

}
