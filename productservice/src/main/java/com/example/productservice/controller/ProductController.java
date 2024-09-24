package com.example.productservice.controller;

import com.example.productservice.dtos.CreateProductRequestDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.dtos.UpdateProductRequestDto;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("ProductService")
    private ProductService productService;



    @PostMapping("/")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody CreateProductRequestDto product) {
        return ResponseEntity.status(201).body(productService.CreateProduct(product));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.GetAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable UUID id ) {
        return ResponseEntity.status(200).body(productService.GetProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable UUID id, @RequestBody UpdateProductRequestDto product) {
        return ResponseEntity.status(200).body(productService.UpdateProduct(product,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted product successfully");
    }

}
