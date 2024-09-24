package com.example.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderRequestdto {
    private UUID customerId;
    private List<CreateOrderItem> orderItems;
}
