package com.example.productservice.service.serviceImpl;

import com.example.productservice.dtos.CategoryResponseDto;
import com.example.productservice.dtos.CreateCategoryRequestDto;
import com.example.productservice.dtos.UpdateCategoryRequestDto;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDto CreateCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = CreateCategoryRequestDto.from(createCategoryRequestDto);
        return CategoryResponseDto.from(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponseDto> GetAllCategory() {
        return categoryRepository.findAll().stream().map(CategoryResponseDto::from).toList();
    }

    @Override
    public CategoryResponseDto GetCategoryById(UUID id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).map(CategoryResponseDto::from).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public CategoryResponseDto UpdateCategory(UpdateCategoryRequestDto updateCategoryRequestDto, UUID productId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(productId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        category.setCategoryName(updateCategoryRequestDto.getCategoryName());
        category.setDescription(updateCategoryRequestDto.getCategoryDescription());
        return CategoryResponseDto.from(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
