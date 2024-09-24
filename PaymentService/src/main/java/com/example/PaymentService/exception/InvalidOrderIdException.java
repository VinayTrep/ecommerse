package com.example.PaymentService.exception;


public class InvalidOrderIdException extends RuntimeException{
    public InvalidOrderIdException() {
    }

    public InvalidOrderIdException(String message) {
        super(message);
    }
}
