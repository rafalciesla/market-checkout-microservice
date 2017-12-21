package com.ciesla.marketcheckoutmicroservice.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {

    private Basket testedObject;

    private Set<Product> productList = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        testedObject = new Basket();
        productList.add(new Product());
        testedObject.setId(1);
        testedObject.setProductList(productList);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(1);
        assertThat(testedObject.getProductList()).isNotNull();
        assertThat(testedObject.getProductList().size()).isEqualTo(1);
    }
}
