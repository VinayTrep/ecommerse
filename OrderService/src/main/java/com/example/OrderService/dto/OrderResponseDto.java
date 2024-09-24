package com.example.OrderService.dto;

import com.example.OrderService.model.Order;
import com.example.OrderService.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDto {
    private UUID orderId;
    private List<OrderItemResponseDto> orderItems;
    private String billingAddress;
    private String shippingAddress;
    private long totalOrderAmount;
    private OrderStatus orderStatus;

    public static OrderResponseDto fromOrder(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setOrderItems(order.getOrderItems().stream().map(OrderItemResponseDto::from).toList());
        orderResponseDto.setBillingAddress(order.getBillingAddress());
        orderResponseDto.setShippingAddress(order.getShippingAddress());
        orderResponseDto.setTotalOrderAmount(order.getTotalOrderAmount());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        return orderResponseDto;
    }
}
