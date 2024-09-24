package com.example.productservice.dtos;

import com.example.productservice.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDto {
    private UUID categoryId;
    private String categoryName;
    private String description;
    private List<ProductResponseDto> Products;

    public static CategoryResponseDto from(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setCategoryId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescription(category.getDescription());
        dto.setProducts(category.getProducts().stream().map(ProductResponseDto::from).toList());
        return dto;
    }
}
