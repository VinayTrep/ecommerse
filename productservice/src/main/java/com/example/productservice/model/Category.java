package com.example.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseClass{
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    private String description;
    @OneToMany(mappedBy = "productCategory",fetch = FetchType.LAZY)
    private List<Product> products;
}
