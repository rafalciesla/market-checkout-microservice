package com.ciesla.marketcheckoutmicroservice.repository;

import com.ciesla.marketcheckoutmicroservice.entity.Basket;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface ProductRepository extends CrudRepository<Product, Integer> {
    Set<Product> findAll();
    Set<Product> findProductsByNameAndBasket(String name, Basket basket);
    Product findProductById(Integer id);
    Product deleteProductById(Integer id);
    Product findProductByName(String name);
}
