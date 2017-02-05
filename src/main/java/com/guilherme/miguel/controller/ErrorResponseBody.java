package com.guilherme.miguel.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by mguilherme on 05-02-2017.
 */
@Data
@AllArgsConstructor
public class ErrorResponseBody {

    String errorCode;
    String errorMessage;

}
