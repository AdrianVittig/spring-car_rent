package com.vitig.car_rent.data.exception;

public class CarAlreadyAvailableException extends RuntimeException {
    public CarAlreadyAvailableException(String message) {
        super(message);
    }
}
