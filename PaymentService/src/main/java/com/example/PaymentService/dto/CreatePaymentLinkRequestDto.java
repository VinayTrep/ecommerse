package com.example.PaymentService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePaymentLinkRequestDto {
    private UUID userId;
    private UUID orderId;
}
