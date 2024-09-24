package com.example.productservice.service;

import com.example.productservice.dtos.CreateCategoryRequestDto;
import com.example.productservice.dtos.CategoryResponseDto;
import com.example.productservice.dtos.UpdateCategoryRequestDto;
import com.example.productservice.exception.CategoryNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDto CreateCategory(CreateCategoryRequestDto createCategoryRequestDto);
    List<CategoryResponseDto> GetAllCategory();
    CategoryResponseDto GetCategoryById(UUID id) throws CategoryNotFoundException;
    CategoryResponseDto UpdateCategory(UpdateCategoryRequestDto updateCategoryRequestDto,UUID productId) throws CategoryNotFoundException;
    void deleteCategory(UUID id);
}
