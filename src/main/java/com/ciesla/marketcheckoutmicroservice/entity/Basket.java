package com.ciesla.marketcheckoutmicroservice.entity;

import com.ciesla.marketcheckoutmicroservice.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Basket {

    @Autowired
    private ProductRepository products;
}
