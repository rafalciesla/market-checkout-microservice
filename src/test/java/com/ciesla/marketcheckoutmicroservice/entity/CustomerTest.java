package com.ciesla.marketcheckoutmicroservice.entity;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    private Customer testedObject;

    private static final Integer ID = 600;
    private static final String FIRST_NAME = "Will";
    private static final String LAST_NAME = "You";
    private static final String EMAIL = "hire@me.com";
    private static final String PASSWORD = "question_mark";
    private static final Basket BASKET = new Basket();

    @Before
    public void setUp() throws Exception {
        testedObject = new Customer();
        testedObject.setId(ID);
        testedObject.setFirstName(FIRST_NAME);
        testedObject.setLastName(LAST_NAME);
        testedObject.setEmail(EMAIL);
        testedObject.setPassword(PASSWORD);
        testedObject.setBasket(BASKET);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(600);
        assertThat(testedObject.getFirstName()).isEqualTo("Will");
        assertThat(testedObject.getLastName()).isEqualTo("You");
        assertThat(testedObject.getEmail()).isEqualTo("hire@me.com");
        assertThat(testedObject.getPassword()).isEqualTo("question_mark");
        assertThat(testedObject.getBasket()).isEqualTo(BASKET);

    }
}
