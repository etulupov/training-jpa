package com.noveogroup.tulupov.addressbook.exception;

/**
 * Group is used exception.
 */
public class GroupIsUsedException extends RuntimeException {
    public GroupIsUsedException() {
    }

    public GroupIsUsedException(final String message) {
        super(message);
    }

    public GroupIsUsedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
