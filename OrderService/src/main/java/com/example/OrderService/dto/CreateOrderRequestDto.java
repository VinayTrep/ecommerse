package com.example.OrderService.dto;

import com.example.OrderService.model.Order;
import com.example.OrderService.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderRequestDto {
    private UUID customerId;
    private List<CreateOrderItem> orderItems;
    private String billingAddress;
    private String shippingAddress;
    private long totalOrderAmount;

    public static Order fromDto(CreateOrderRequestDto createOrderRequestDto) {
        Order order = new Order();
        order.setCustomerId(createOrderRequestDto.getCustomerId());
        order.setBillingAddress(createOrderRequestDto.getBillingAddress());
        order.setShippingAddress(createOrderRequestDto.getShippingAddress());
        order.setTotalOrderAmount(createOrderRequestDto.getTotalOrderAmount());
        return order;
    }
}
