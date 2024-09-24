package com.example.PaymentService.dto;

import com.example.PaymentService.client.dtos.OrderServiceOrderResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderResponseDto {
    private UUID orderId;
    private String billingAddress;
    private String shippingAddress;
    private long amount;

    public static OrderResponseDto from(OrderServiceOrderResponseDto orderServiceOrderResponseDto) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(orderServiceOrderResponseDto.getOrderId());
        orderResponseDto.setBillingAddress(orderServiceOrderResponseDto.getBillingAddress());
        orderResponseDto.setShippingAddress(orderServiceOrderResponseDto.getShippingAddress());
        orderResponseDto.setAmount(orderResponseDto.getAmount());
        return orderResponseDto;
    }
}
