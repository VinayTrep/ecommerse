package com.example.productservice.exception;


public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException() {
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }
}
