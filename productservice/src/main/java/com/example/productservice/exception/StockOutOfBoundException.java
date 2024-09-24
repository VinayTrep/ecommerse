package com.example.productservice.exception;

public class StockOutOfBoundException extends RuntimeException{
    public StockOutOfBoundException() {
    }

    public StockOutOfBoundException(String message) {
        super(message);
    }
}
