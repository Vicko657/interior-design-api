package com.interiordesignplanner.exception;

import java.text.MessageFormat;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long id) {
        super(MessageFormat.format("Client with {0} id was not found", id));

    }

}
