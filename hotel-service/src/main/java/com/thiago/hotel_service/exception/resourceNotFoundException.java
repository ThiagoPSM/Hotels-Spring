package com.thiago.hotel_service.exception;

public class resourceNotFoundException extends RuntimeException{
    public resourceNotFoundException(String message) {
        super(message);
    }
}
