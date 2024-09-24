package com.example.PaymentService.client.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderServiceOrderResponseDto {
    private UUID orderId;
    private List<OrderItemResponseDto> orderItems;
    private String billingAddress;
    private String shippingAddress;
    private long totalOrderAmount;
    private OrderStatus orderStatus;
}
