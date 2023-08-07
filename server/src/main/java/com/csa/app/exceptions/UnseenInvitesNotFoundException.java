package com.csa.app.exceptions;

public class UnseenInvitesNotFoundException extends RuntimeException {
    public UnseenInvitesNotFoundException(String message) {
        super(message);
    }
}
