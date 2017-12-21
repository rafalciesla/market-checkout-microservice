package com.ciesla.marketcheckoutmicroservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    private MainController testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new MainController();
    }

    @Test
    public void shouldReturnHelloMessage() {
        String message = testedObject.mainPage();
        assertThat(message).isEqualTo("Welcome to market checkout microservice");
    }
}
