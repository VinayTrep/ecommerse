package com.example.productservice.dtos;

import com.example.productservice.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CreateCategoryRequestDto {
    private String categoryName;
    private String description;

    public static Category from(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = new Category();
        category.setCategoryName(createCategoryRequestDto.getCategoryName());
        category.setDescription(createCategoryRequestDto.getDescription());
        category.setProducts(new ArrayList<>());
        return category;
    }
}
