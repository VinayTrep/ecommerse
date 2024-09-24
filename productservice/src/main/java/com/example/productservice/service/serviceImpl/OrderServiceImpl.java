package com.example.productservice.service.serviceImpl;

import com.example.productservice.client.orderServiceClient.OrderServiceClient;
import com.example.productservice.client.orderServiceClient.dtos.OrderServiceCreateOrderItem;
import com.example.productservice.client.orderServiceClient.dtos.OrderServiceCreateOrderRequestDto;
import com.example.productservice.dtos.CreateInventoryRequestDto;
import com.example.productservice.dtos.CreateOrderItem;
import com.example.productservice.dtos.CreateOrderRequestdto;
import com.example.productservice.dtos.InventoryResponseDto;
import com.example.productservice.service.InventoryService;
import com.example.productservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Override
    public String placeOrder(CreateOrderRequestdto requestDto) {

        OrderServiceCreateOrderRequestDto orderServiceCreateOrderRequestDto = new OrderServiceCreateOrderRequestDto();
        orderServiceCreateOrderRequestDto.setCustomerId(requestDto.getCustomerId());
        orderServiceCreateOrderRequestDto.setBillingAddress("Doddaballapur");
        orderServiceCreateOrderRequestDto.setShippingAddress("Doddaballapur");
        List<OrderServiceCreateOrderItem> orderServiceCreateOrderItems = new ArrayList<>();
        long totalPrice = 0;

        List<CreateOrderItem> OrderItem = requestDto.getOrderItems();
        for (CreateOrderItem createOrderItem : OrderItem) {
            UUID productId = createOrderItem.getProductId();
            // update product inventory
            CreateInventoryRequestDto decrementInventory = new CreateInventoryRequestDto();
            decrementInventory.setProductId(productId);
            decrementInventory.setQuantity(createOrderItem.getQuantity());
            InventoryResponseDto inventoryResponseDto = inventoryService.decrementInventory(decrementInventory);

            // update the orderServiceCreateOrderDto
            totalPrice += (createOrderItem.getQuantity() * inventoryResponseDto.getPrice());
            OrderServiceCreateOrderItem newOrderServiceCreateOrderItem = new OrderServiceCreateOrderItem();
            newOrderServiceCreateOrderItem.setProductId(productId);
            newOrderServiceCreateOrderItem.setQuantity(createOrderItem.getQuantity());
            newOrderServiceCreateOrderItem.setPrice(createOrderItem.getQuantity() * inventoryResponseDto.getPrice());
            orderServiceCreateOrderItems.add(newOrderServiceCreateOrderItem);

        }
        orderServiceCreateOrderRequestDto.setOrderItems(orderServiceCreateOrderItems);
        orderServiceCreateOrderRequestDto.setTotalOrderAmount(totalPrice);
        System.out.println("==============");
        System.out.println(totalPrice);
        System.out.println("==========");
        return orderServiceClient.createOrder(orderServiceCreateOrderRequestDto);
    }
}
