package com.example.productservice.dtos;

import com.example.productservice.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDto {
    private UUID productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private String productCategory;
    private String productSize;
    private String imageUrl;

    public static ProductResponseDto from(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductId(product.getId());
        productResponseDto.setProductCategory(product.getProductCategory().getCategoryName());
        productResponseDto.setProductDescription(product.getProductDescription());
        productResponseDto.setProductPrice(product.getProductPrice());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setProductDescription(product.getProductDescription());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setProductSize(product.getProductSize().toString());
        return productResponseDto;
    }
}
