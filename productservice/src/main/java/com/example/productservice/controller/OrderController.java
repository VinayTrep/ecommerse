package com.example.productservice.controller;

import com.example.productservice.dtos.CreateOrderRequestdto;
import com.example.productservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequestdto requestDto) {
        return  ResponseEntity.ok(orderService.placeOrder(requestDto));
    }
}
