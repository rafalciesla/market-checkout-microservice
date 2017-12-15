package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
