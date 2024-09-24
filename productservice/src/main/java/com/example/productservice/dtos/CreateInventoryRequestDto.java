package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateInventoryRequestDto {
    private UUID productId;
    private int quantity;
}
