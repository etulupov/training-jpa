package com.noveogroup.tulupov.addressbook.exception;

/**
 * Group not found exception.
 */
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
    }

    public GroupNotFoundException(final String message) {
        super(message);
    }

    public GroupNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
