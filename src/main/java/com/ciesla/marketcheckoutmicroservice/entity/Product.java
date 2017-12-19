package com.ciesla.marketcheckoutmicroservice.entity;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private ProductType productType;

    @ManyToOne
    @JsonBackReference
    private Basket basket;

    public Product() {
    }

    public Product(String name, Double price, ProductType productType) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }
    public Product(String name, Double price, ProductType productType, Basket basket) {
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", productType=" + productType +
                '}';
    }
}
