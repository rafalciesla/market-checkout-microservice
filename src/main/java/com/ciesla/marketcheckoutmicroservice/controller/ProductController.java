package com.ciesla.marketcheckoutmicroservice.controller;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/product/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("find/all")
    public Set<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("add/" + "{name}" + "/" + "{price}" + "/" + "{type}" + "/" + "{quantity}")
    public ResponseEntity<String> addNewProductToDatabase(@PathVariable("name") String name,
                                                  @PathVariable("price") Double price,
                                                  @PathVariable("type") Integer type,
                                                  @PathVariable("quantity") Integer quantity) {

        Product product = new Product(name, price, ProductType.fromInteger(type));
        productService.addNewProductToDatabase(product, quantity);
        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }
}
