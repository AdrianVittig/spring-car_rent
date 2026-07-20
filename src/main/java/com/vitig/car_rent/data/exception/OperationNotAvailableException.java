package com.vitig.car_rent.data.exception;

public class OperationNotAvailableException extends RuntimeException {
    public OperationNotAvailableException(String message) {
        super(message);
    }
}
