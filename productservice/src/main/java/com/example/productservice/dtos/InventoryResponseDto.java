package com.example.productservice.dtos;

import com.example.productservice.model.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponseDto {
    private String productId;
    private String productName;
    private int quantity;
    private long price;

    public static InventoryResponseDto fromInventory(Inventory inventory) {
        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setProductId(inventory.getProduct().getId().toString());
        dto.setProductName(inventory.getProduct().getProductName());
        dto.setQuantity(inventory.getQuantity());
        dto.setPrice(inventory.getProduct().getProductPrice());
        return dto;
    }
}
