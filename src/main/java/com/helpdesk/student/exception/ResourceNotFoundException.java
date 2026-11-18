package com.helpdesk.student.exception;

/**
 * Custom runtime exception thrown when a requested resource (e.g. Ticket) is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

