package com.example.productservice.service.serviceImpl;

import com.example.productservice.dtos.CreateInventoryRequestDto;
import com.example.productservice.dtos.InventoryResponseDto;
import com.example.productservice.exception.InventoryNotFoundException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.exception.ProductOutOfStockException;
import com.example.productservice.exception.StockOutOfBoundException;
import com.example.productservice.model.Inventory;
import com.example.productservice.model.Product;
import com.example.productservice.repository.InventoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public InventoryResponseDto incrementInventory(CreateInventoryRequestDto requestDto) throws ProductNotFoundException {
        UUID productId = requestDto.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product with given id does not exist"));
        Optional<Inventory> optionalInventory = inventoryRepository.findByProduct(product);
        Inventory inventory;
        if (optionalInventory.isEmpty()) {
            inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setQuantity(requestDto.getQuantity());
            return InventoryResponseDto.fromInventory(inventoryRepository.save(inventory));
        }
        inventory = optionalInventory.get();
        inventory.setQuantity(inventory.getQuantity() + requestDto.getQuantity());
        return InventoryResponseDto.fromInventory(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponseDto decrementInventory(CreateInventoryRequestDto requestDto) throws ProductNotFoundException {
        System.out.println("========================================");
        System.out.println("Request came");
        System.out.println("=========================================");
        UUID productId = requestDto.getProductId();
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()-> new InventoryNotFoundException("Inventory with given product does not exist"));

        if(inventory.getQuantity() < requestDto.getQuantity()){
            throw new StockOutOfBoundException("Inventory does not have enough stock");
        }

        inventory.setQuantity(inventory.getQuantity() - requestDto.getQuantity());
        return InventoryResponseDto.fromInventory(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponseDto getInventory(UUID productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product with given id does not exist"));
        return InventoryResponseDto.fromInventory(inventoryRepository.findByProduct(product).orElseThrow(()-> new InventoryNotFoundException("Product with given id does not exist")));
    }

    @Override
    public void deleteInventory(UUID productId) throws ProductNotFoundException {
        inventoryRepository.deleteByProductId(productId);
    }

    @Override
    public InventoryResponseDto findByProductIdAndQuantityGreaterThan(UUID productId, Integer quantity) throws ProductOutOfStockException {
        return InventoryResponseDto.fromInventory(inventoryRepository.findByProductIdAndQuantityGreaterThanEqual(productId,quantity)
                .orElseThrow(()-> new ProductOutOfStockException("Product with given id is out of stock")));
    }
}
