package com.ciesla.marketcheckoutmicroservice.repository;

import com.ciesla.marketcheckoutmicroservice.entity.Basket;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepository extends CrudRepository<Basket, Integer> {
    Basket findBasketById(Integer id);
}
