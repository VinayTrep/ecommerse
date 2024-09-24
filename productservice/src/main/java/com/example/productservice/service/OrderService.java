package com.example.productservice.service;

import com.example.productservice.client.orderServiceClient.dtos.OrderServiceCreateOrderRequestDto;
import com.example.productservice.dtos.CreateOrderRequestdto;

public interface OrderService {
    public String placeOrder(CreateOrderRequestdto requestDto);
}
