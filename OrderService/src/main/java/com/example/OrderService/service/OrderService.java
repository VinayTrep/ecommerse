package com.example.OrderService.service;

import com.example.OrderService.dto.CreateOrderRequestDto;
import com.example.OrderService.dto.OrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    public String createOrder(CreateOrderRequestDto requestDto);
    public OrderResponseDto getOrder(UUID orderId);
    public List<OrderResponseDto> getAllOrders();
    public String deleteOrder(UUID orderId);
}
