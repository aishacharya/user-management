package com.example.user_management.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The type User not found exception.
 */
@Slf4j
public class UserNotFoundException extends RuntimeException {

    /**
     * Instantiates a new User not found exception.
     *
     * @param message the message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
