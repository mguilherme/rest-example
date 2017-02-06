package com.guilherme.miguel.exception;

import lombok.Getter;

/**
 * User Not Found Exception
 *
 * @author Miguel Guilherme
 */
public class UserNotFoundException extends RuntimeException {

    @Getter
    Long id;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
