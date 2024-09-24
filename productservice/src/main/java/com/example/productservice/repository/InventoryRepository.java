package com.example.productservice.repository;

import com.example.productservice.model.Inventory;
import com.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    Optional<Inventory> findByProduct(Product product);
    Optional<Inventory> findByProductId(UUID productId);
    void deleteByProductId(UUID productId);
    Optional<Inventory> findByProductIdAndQuantityGreaterThanEqual(UUID productId, Integer quantity);
}
