package com.example.bloodplatform.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String type, UUID id) {
        super(type + " not found: " + id);
    }
}
