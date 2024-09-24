package com.example.OrderService.exception;

public class InvalidOrderIdException extends RuntimeException {
    public InvalidOrderIdException() {
    }

    public InvalidOrderIdException(String message) {
        super(message);
    }
}
