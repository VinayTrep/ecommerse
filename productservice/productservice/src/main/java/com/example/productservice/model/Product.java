package com.example.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseClass{
    private String productName;
    private String productDescription;
    @ManyToOne
    private Category category;
    private long productPrice;
    private String productSize;
    private String imageUrl;
}
