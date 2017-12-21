package com.ciesla.marketcheckoutmicroservice.entity;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private Product testedObject;

    private static final Integer ID = 6;
    private static final String NAME = "testPepsi";
    private static final Double PRICE = 10.0;
    private static final ProductType PRODUCT_TYPE = ProductType.SODA;
    private static final Basket BASKET = new Basket();

    @Before
    public void setUp() throws Exception {
        testedObject = new Product();
        testedObject.setId(ID);
        testedObject.setName(NAME);
        testedObject.setPrice(PRICE);
        testedObject.setProductType(PRODUCT_TYPE);
        testedObject.setBasket(BASKET);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(6);
        assertThat(testedObject.getName()).isEqualTo("testPepsi");
        assertThat(testedObject.getPrice()).isEqualTo(10.0);
        assertThat(testedObject.getProductType()).isEqualTo(ProductType.SODA);
        assertThat(testedObject.getBasket()).isEqualTo(BASKET);
    }
}
