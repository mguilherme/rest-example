package com.guilherme.miguel.controller;

import lombok.Value;

/**
 * Error Response
 *
 * @author Miguel Guilherme
 */
@Value
public class ErrorResponseBody {

    String errorCode;
    String errorMessage;

}
