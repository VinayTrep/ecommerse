package com.example.productservice.client.orderServiceClient.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class OrderServiceCreateOrderItem {
    private UUID productId;
    private int quantity;
    private long price;
}
