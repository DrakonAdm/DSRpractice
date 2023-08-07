package com.csa.app.exceptions;


public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
