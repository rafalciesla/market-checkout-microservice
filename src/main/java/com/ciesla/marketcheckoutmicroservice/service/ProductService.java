package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Set<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Transactional
    public void addNewProductToDatabase(Product product, Integer quantity) {
        while(quantity > 0) {
            productRepository.save(product);
            quantity--;
        }
    }
}
