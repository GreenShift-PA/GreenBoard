package com.greenshift.greenboard.exceptions;

public class GenericHTTPException extends Exception {
    public GenericHTTPException(Integer statusCode, String message) {
        super(String.format("HTTP %d: %s", statusCode, message));
    }
}
