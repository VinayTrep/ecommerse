package com.example.OrderService.dto;

import com.example.OrderService.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class CreateOrderItem {
    private UUID productId;
    private int quantity;
    private long price;

    public static OrderItem fromOrder(CreateOrderItem createOrderItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(createOrderItem.getProductId());
        orderItem.setQuantity(createOrderItem.getQuantity());
        orderItem.setPrice(createOrderItem.getPrice());
        return orderItem;
    }
}
