package com.guilherme.miguel.exception;

/**
 * Created by mguilherme on 05-02-2017.
 */
public class UserNotFoundException extends RuntimeException {

    private Long id;

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

    public Long getId() {
        return id;
    }
}
