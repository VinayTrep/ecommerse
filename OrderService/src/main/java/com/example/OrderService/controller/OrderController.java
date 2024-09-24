package com.example.OrderService.controller;

import com.example.OrderService.dto.CreateOrderRequestDto;
import com.example.OrderService.dto.OrderResponseDto;
import com.example.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.createOrder(requestDto));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.status(200).body(orderService.getOrder(orderId));
    }

    @GetMapping("/")
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.status(200).body(orderService.deleteOrder(orderId));
    }

}
