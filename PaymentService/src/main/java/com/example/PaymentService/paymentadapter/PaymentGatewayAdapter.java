package com.example.PaymentService.paymentadapter;

import com.example.PaymentService.dto.OrderResponseDto;

import java.util.UUID;

public interface PaymentGatewayAdapter {
    public String makePayment(OrderResponseDto orderResponseDto, UUID paymentId, long amount);
}
