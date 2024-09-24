package com.example.OrderService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "orders")
public class Order extends BaseModel {
    private UUID customerId;
    private UUID paymentId;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    private String billingAddress;
    private String shippingAddress;
    private long totalOrderAmount;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
}
