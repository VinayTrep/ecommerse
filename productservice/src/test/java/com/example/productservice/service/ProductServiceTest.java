package com.example.productservice.service;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    @Test
    public void findAll() {
        Product products = productRepository.findById(UUID.fromString("324cd7d5-9e05-4190-876b-eb9537f23deb")).get();
        System.out.println(products);
        System.out.println("after fetching product");
        System.out.println(products.getProductCategory());
    }
}
