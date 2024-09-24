package com.example.OrderService.service.serviceImpl;

import com.example.OrderService.client.PaymentServiceClient;
import com.example.OrderService.client.dto.paymentDto.GeneratePaymentLinkDto;
import com.example.OrderService.dto.CreateOrderItem;
import com.example.OrderService.dto.CreateOrderRequestDto;
import com.example.OrderService.dto.OrderResponseDto;
import com.example.OrderService.exception.InvalidOrderIdException;
import com.example.OrderService.model.Order;
import com.example.OrderService.model.OrderItem;
import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderItemsRepository;
import com.example.OrderService.repository.OrderRepository;
import com.example.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  OrderItemsRepository orderItemsRepository;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Override
    public String createOrder(CreateOrderRequestDto requestDto) {
        Order order = CreateOrderRequestDto.fromDto(requestDto);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus(OrderStatus.ORDER_PLACED);
        Order savedOrder = orderRepository.save(order);
        List<OrderItem> orderItems = new ArrayList<>();
        for (CreateOrderItem createOrderItem: requestDto.getOrderItems()) {
            OrderItem orderItem = CreateOrderItem.fromOrder(createOrderItem);
            orderItem.setOrder(savedOrder);
            orderItems.add(orderItemsRepository.save(orderItem));
        }
        savedOrder.setOrderItems(orderItems);
        orderRepository.save(savedOrder);

        GeneratePaymentLinkDto getPaymentLinkDto = new GeneratePaymentLinkDto();
        getPaymentLinkDto.setOrderId(savedOrder.getId());
        getPaymentLinkDto.setUserId(requestDto.getCustomerId());
        return paymentServiceClient.generatePaymentUrl(getPaymentLinkDto);
    }

    @Override
    public OrderResponseDto getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new InvalidOrderIdException("Order with given order id does not exist" + orderId));
        return OrderResponseDto.fromOrder(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderResponseDto::fromOrder).toList();
    }

    @Override
    public String deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new InvalidOrderIdException("Order with given order id does not exist" + orderId)
        );
        order.setActive(false);
        orderRepository.save(order);
        return "Order deleted SUCCESSFULLY";
    }
}
