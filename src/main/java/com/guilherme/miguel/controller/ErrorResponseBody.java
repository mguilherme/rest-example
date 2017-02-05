package com.guilherme.miguel.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Error Response
 *
 * @author Miguel Guilherme
 */
@Data
@AllArgsConstructor
public class ErrorResponseBody {

    String errorCode;
    String errorMessage;

}
