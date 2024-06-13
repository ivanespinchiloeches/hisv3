package com.ivan.thehealthsoftwarecompany.hisv3.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends RuntimeException {

    Logger LOG = LoggerFactory.getLogger(UserNotFoundException.class);
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public UserNotFoundException() {
       // LOG.debug("******* UserNotFoundException probably due to a not valid input");

        super("User not found");
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UserNotFoundException(String message) {
        super(message);
    }


}
