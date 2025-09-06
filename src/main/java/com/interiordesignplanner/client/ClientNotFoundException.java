package com.interiordesignplanner.client;

import java.text.MessageFormat;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long id) {
        super(MessageFormat.format("Client with {0} id was not found", id));

    }

    public ClientNotFoundException(String lastName) {
        super(("No clients found with the lastname " + lastName));

    }

}
