package com.csa.app.exceptions;

public class ChatRoomsNotFoundException extends RuntimeException {

    public ChatRoomsNotFoundException(String message) {
        super(message);
    }
}
