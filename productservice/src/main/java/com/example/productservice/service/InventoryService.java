package com.example.productservice.service;

import com.example.productservice.dtos.CreateInventoryRequestDto;
import com.example.productservice.dtos.InventoryResponseDto;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.exception.ProductOutOfStockException;

import java.util.UUID;

public interface InventoryService {
    public InventoryResponseDto incrementInventory(CreateInventoryRequestDto requestDto) throws ProductNotFoundException;
    public InventoryResponseDto decrementInventory(CreateInventoryRequestDto requestDto) throws ProductNotFoundException;
    public InventoryResponseDto getInventory(UUID productId) throws ProductNotFoundException;
    public void deleteInventory(UUID productId) throws ProductNotFoundException;
    public InventoryResponseDto findByProductIdAndQuantityGreaterThan(UUID productId, Integer quantity) throws ProductOutOfStockException;
}
