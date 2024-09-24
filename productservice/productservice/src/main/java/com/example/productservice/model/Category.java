package com.example.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseClass{
    private String categoryName;
    private String description;
    @OneToMany
    private List<Product> products;
}
