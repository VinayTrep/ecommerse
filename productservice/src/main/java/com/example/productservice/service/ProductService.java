package com.example.productservice.service;

import com.example.productservice.dtos.CreateProductRequestDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.dtos.UpdateProductRequestDto;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDto CreateProduct(CreateProductRequestDto createProductRequestDto) throws CategoryNotFoundException;
    List<ProductResponseDto> GetAllProducts();
    ProductResponseDto GetProductById(UUID id) throws ProductNotFoundException;
    ProductResponseDto UpdateProduct(UpdateProductRequestDto updateProductRequestDto,UUID productId) throws ProductNotFoundException, CategoryNotFoundException;
    void deleteProduct(UUID id);
    List<ProductResponseDto> GetProductsByCategoryId(UUID id);
}
