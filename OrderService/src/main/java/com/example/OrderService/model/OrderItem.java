package com.example.OrderService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "order_items")
public class OrderItem extends BaseModel{
    private UUID productId;
    private int quantity;
    private long price;
    @ManyToOne
    private Order order;
}
