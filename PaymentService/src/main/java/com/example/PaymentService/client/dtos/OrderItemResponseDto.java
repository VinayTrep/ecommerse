package com.example.PaymentService.client.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemResponseDto {
    private UUID productId;
    private int quantity;
    private long price;
}
