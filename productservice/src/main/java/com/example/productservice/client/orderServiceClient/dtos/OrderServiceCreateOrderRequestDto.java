package com.example.productservice.client.orderServiceClient.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderServiceCreateOrderRequestDto {
    private UUID customerId;
    private List<OrderServiceCreateOrderItem> orderItems;
    private String billingAddress;
    private String shippingAddress;
    private long totalOrderAmount;
}
