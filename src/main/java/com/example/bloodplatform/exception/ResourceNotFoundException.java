package com.example.bloodplatform.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String type, UUID id) {
        super(buildMessage(type, id));
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    private static String buildMessage(String type, UUID id) {
        if (id == null) return type + " not found";
        return type + " not found: " + id;
    }
}