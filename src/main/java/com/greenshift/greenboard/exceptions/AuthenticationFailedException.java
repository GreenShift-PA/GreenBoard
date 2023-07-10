package com.greenshift.greenboard.exceptions;

public class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException() {
        super("Authentication failed.");
    }
}

