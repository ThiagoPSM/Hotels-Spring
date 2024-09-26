package com.thiago.hotel_service.exception;

public class hotelNotFoundException extends RuntimeException{
    public hotelNotFoundException(String message) {
        super(message);
    }
}
