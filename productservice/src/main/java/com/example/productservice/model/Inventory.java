package com.example.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "inventories")
public class Inventory extends BaseClass{
    @OneToOne
    private Product product;
    private int quantity;
}
