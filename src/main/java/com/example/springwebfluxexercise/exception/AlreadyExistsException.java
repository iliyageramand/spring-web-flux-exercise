package com.example.springwebfluxexercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;


public class AlreadyExistsException extends ErrorResponseException {
    public AlreadyExistsException(String message) {
        this(message, null);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message), cause);
    }
}
