package com.ciesla.marketcheckoutmicroservice.repository;

import com.ciesla.marketcheckoutmicroservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
}
