package com.guilherme.miguel.controller;

import com.guilherme.miguel.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.lang.String.format;

/**
 * Global Exception Handler
 *
 * @author Miguel Guilherme
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFound(UserNotFoundException e) {
        log.error("User {} not found", e.getId());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseBody("404 - Not Found", format("User %s not found", e.getId())));
    }

}
