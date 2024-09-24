package com.example.PaymentService.exception;

public class CreatePaymentLinkException extends RuntimeException{
    public CreatePaymentLinkException() {
    }

    public CreatePaymentLinkException(String message) {
        super(message);
    }
}
