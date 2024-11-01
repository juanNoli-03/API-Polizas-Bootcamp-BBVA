package com.bbva.tp_integrador_java.E_exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus status;

    public CustomException(final HttpStatus httpStatus, final String message) {
        super(message);
        this.status = httpStatus;
    }
}