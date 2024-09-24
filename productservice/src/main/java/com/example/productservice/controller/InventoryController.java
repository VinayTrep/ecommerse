package com.example.productservice.controller;

import com.example.productservice.dtos.CreateInventoryRequestDto;
import com.example.productservice.dtos.InventoryResponseDto;
import com.example.productservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponseDto> getInventory(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(inventoryService.getInventory(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") UUID id) {
        inventoryService.deleteInventory(id);
        return  ResponseEntity.ok("Inventory deleted successfully");
    }
}
