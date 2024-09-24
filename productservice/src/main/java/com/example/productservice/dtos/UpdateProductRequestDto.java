package com.example.productservice.dtos;

import com.example.productservice.model.Product;
import com.example.productservice.model.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateProductRequestDto {
    private String productName;
    private String productDescription;
    private long productPrice;
    private UUID categoryId;
    private String productSize;
    private String imageUrl;
    private String brand;

    public  static Product fromDto(UpdateProductRequestDto updateProductRequestDto){
        Product product = new Product();
        product.setProductName(updateProductRequestDto.getProductName());
        product.setProductDescription(updateProductRequestDto.getProductDescription());
        product.setProductPrice(updateProductRequestDto.getProductPrice());
        product.setBrand(updateProductRequestDto.getBrand());
        product.setProductSize(Size.valueOf(updateProductRequestDto.getProductSize()));
        product.setImageUrl(updateProductRequestDto.getImageUrl());
        return product;
    }
}
