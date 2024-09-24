package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequestDto {
    private String categoryName;
    private String categoryDescription;
}
