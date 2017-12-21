package com.ciesla.marketcheckoutmicroservice.controller;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private ProductController testedObject;

    @Mock
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        testedObject = new ProductController(productService);
    }

    @Test
    public void shouldReturnListOfProducts() throws Exception {
        when(productService.findAllProducts()).thenReturn(getProducts());

        Set<Product> foundProducts = testedObject.findAllProducts();
        verify(productService, times(1)).findAllProducts();

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.size()).isEqualTo(3);
    }

    @Test
    public void shouldAddNewProductToProductDatabase() {
        ResponseEntity<String> desiredResponse = new ResponseEntity<>("Product added", HttpStatus.CREATED);

        String name = "testProduct";
        Double price = 10.0;
        Integer productTypeInteger = 6;
        ProductType productType = ProductType.OTHER;
        Integer quantity = 1;

        Product productToAdd = new Product(name, price, productType);
        doNothing().when(productService).addNewProductToDatabase(productToAdd, quantity);

        ResponseEntity<String> response = testedObject.addNewProductToDatabase(name, price, productTypeInteger, quantity);

        assertThat(response).isEqualTo(desiredResponse);
    }

    private Set<Product> getProducts() {
        Set<Product> testProducts = new HashSet<>();
        testProducts.add(new Product("testProduct1", 10.0, ProductType.OTHER));
        testProducts.add(new Product("testProduct2", 10.0, ProductType.FRUIT));
        testProducts.add(new Product("testProduct3", 10.0, ProductType.VEGETABLE));
        return testProducts;
    }
}
