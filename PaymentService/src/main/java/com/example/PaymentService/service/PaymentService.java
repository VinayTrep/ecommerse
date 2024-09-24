package com.example.PaymentService.service;

import com.example.PaymentService.dto.CreatePaymentLinkRequestDto;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public String generatePayment(CreatePaymentLinkRequestDto createPaymentLinkRequestDto);
}
