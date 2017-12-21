package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private ProductService testedObject;

    @Mock
    private ProductRepository mockedProductRepository;

    @Before
    public void setUp() throws Exception {
        testedObject = new ProductService(mockedProductRepository);
    }

    @Test
    public void shouldReturnAllProducts() {
        Set<Product> testProducts = getProducts();

        when(mockedProductRepository.findAll()).thenReturn(testProducts);

        Set<Product> foundProducts = testedObject.findAllProducts();

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.size()).isEqualTo(3);
    }

    @Test
    public void shouldReturnSpecificProductWhenNamePassed() {
        Product productWeLookingFor = getProduct();

        when(mockedProductRepository.findProductByName("testProduct")).thenReturn(productWeLookingFor);

        Product foundProduct = testedObject.findProductByName("testProduct");

        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct).isEqualTo(productWeLookingFor);
    }

    @Test
    public void shouldAddProductToDatabase() {
        Product productToAdd = getProduct();

        when(mockedProductRepository.save(productToAdd)).thenReturn(productToAdd);

        testedObject.addNewProductToDatabase(productToAdd, 1);

        verify(mockedProductRepository).save(productToAdd);
    }

    private Set<Product> getProducts() {
        Set<Product> testProducts = new HashSet<>();
        testProducts.add(new Product("testProduct1", 3.0, ProductType.SODA));
        testProducts.add(new Product("testProduct2", 3.0, ProductType.SODA));
        testProducts.add(new Product("testProduct3", 3.0, ProductType.SODA));

        return testProducts;
    }

    private Product getProduct() {
        return new Product("testProduct", 10.0, ProductType.FRUIT);
    }
}
