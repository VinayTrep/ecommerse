package com.example.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "products")
@Getter
@Setter
public class Product extends BaseClass{
    private String productName;
    private String productDescription;
    private long productPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category productCategory;
    private String brand;
    @Enumerated(EnumType.ORDINAL)
    private Size productSize;
    private String imageUrl;
}
