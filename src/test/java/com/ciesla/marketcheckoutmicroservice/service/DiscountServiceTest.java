package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountServiceTest {

    private DiscountService testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new DiscountService();
    }

    @Test
    public void shouldReturnTotalPriceWhenProductsOfDifferentTypePassed() {
        Set<Product> testProducts = getProductsOfDifferentType();
        Double price = testedObject.getTotalPriceWithDiscounts(testProducts);
        assertThat(price).isEqualTo(50);
    }

    @Test
    public void shouldReturnTotalPriceWhenProductsOfTheSameTypePassed() {
        Set<Product> testProducts = getProductsOfTheSameType();
        Double price = testedObject.getTotalPriceWithDiscounts(testProducts);
        assertThat(price).isEqualTo(40);
    }

    private Set<Product> getProductsOfDifferentType() {
        Set<Product> testProducts = new HashSet<>();
        testProducts.add(new Product("testProduct", 10.0, ProductType.FRUIT));
        testProducts.add(new Product("testProduct", 10.0, ProductType.VEGETABLE));
        testProducts.add(new Product("testProduct", 10.0, ProductType.JUICE));
        testProducts.add(new Product("testProduct", 10.0, ProductType.SODA));
        testProducts.add(new Product("testProduct", 10.0, ProductType.DAIRY_PRODUCT));

        return testProducts;
    }

    private Set<Product> getProductsOfTheSameType() {
        Set<Product> testProducts = new HashSet<>();
        testProducts.add(new Product("testProduct", 10.0, ProductType.FRUIT));
        testProducts.add(new Product("testProduct", 10.0, ProductType.FRUIT));
        testProducts.add(new Product("testProduct", 10.0, ProductType.FRUIT));
        testProducts.add(new Product("testProduct", 10.0, ProductType.FRUIT));
        testProducts.add(new Product("testProduct", 10.0, ProductType.FRUIT));

        return testProducts;
    }
}
