package com.example.productservice.dtos;
import com.example.productservice.model.Product;
import com.example.productservice.model.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Getter
@Setter
public class CreateProductRequestDto {
    private String productName;
    private String productDescription;
    private long productPrice;
    private UUID productCategory;
    private String brand;
    private String productSize;
    private String imageUrl;
    private int inventory;

    public static Product fromDto(CreateProductRequestDto createProductRequestDto) {
        Product product = new Product();
        product.setProductName(createProductRequestDto.getProductName());
        product.setProductDescription(createProductRequestDto.getProductDescription());
        product.setProductPrice(createProductRequestDto.getProductPrice());
        product.setBrand(createProductRequestDto.getBrand());
        product.setProductSize(Size.valueOf(createProductRequestDto.productSize));
        product.setImageUrl(createProductRequestDto.getImageUrl());
        return product;
    }

}
