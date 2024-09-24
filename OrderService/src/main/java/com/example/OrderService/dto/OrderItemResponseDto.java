package com.example.OrderService.dto;

import com.example.OrderService.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemResponseDto {
    private UUID productId;
    private int quantity;
    private long price;

    public static OrderItemResponseDto from(OrderItem orderItem) {
        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        orderItemResponseDto.setProductId(orderItem.getProductId());
        orderItemResponseDto.setQuantity(orderItem.getQuantity());
        orderItemResponseDto.setPrice(orderItem.getPrice());
        return orderItemResponseDto;
    }
}
