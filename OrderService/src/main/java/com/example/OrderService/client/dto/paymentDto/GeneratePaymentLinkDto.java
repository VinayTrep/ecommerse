package com.example.OrderService.client.dto.paymentDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GeneratePaymentLinkDto {
    private UUID userId;
    private UUID orderId;
}