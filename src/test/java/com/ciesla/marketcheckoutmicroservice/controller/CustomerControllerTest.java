package com.ciesla.marketcheckoutmicroservice.controller;

import com.ciesla.marketcheckoutmicroservice.ProductType;
import com.ciesla.marketcheckoutmicroservice.entity.Basket;
import com.ciesla.marketcheckoutmicroservice.entity.Customer;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.service.BasketService;
import com.ciesla.marketcheckoutmicroservice.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    private CustomerController testedObject;

    @Mock
    private CustomerService mockedCustomerService;

    @Mock
    private BasketService mockedBasketService;

    @Before
    public void setUp() throws Exception {
        testedObject = new CustomerController(mockedBasketService, mockedCustomerService);
    }

    @Test
    public void shouldReturnCustomerList() throws Exception {
        when(testedObject.findAllCustomers()).thenReturn(getCustomerList());

        List<Customer> result = testedObject.findAllCustomers();
        verify(mockedCustomerService, times(1)).findAllCustomers();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void shouldReturnSpecificCustomerWhenIdPassed() throws Exception {
        Customer customerWeLookingFor = new Customer();
        customerWeLookingFor.setId(6);

        when(testedObject.findCustomerById(6)).thenReturn(customerWeLookingFor);

        Customer foundCustomer = testedObject.findCustomerById(6);

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer).isEqualTo(customerWeLookingFor);
    }

    @Test
    public void shouldAddNewCustomer() throws Exception {
        ResponseEntity<String> desiredResponse = new ResponseEntity<>("New customer has been added", HttpStatus.CREATED);
        Customer customerToAdd = new Customer();

        doNothing().when(mockedCustomerService).addNewCustomer(customerToAdd);
        ResponseEntity<String> response = testedObject.addNewCustomer(customerToAdd.getFirstName(),
                                                                      customerToAdd.getLastName(),
                                                                      customerToAdd.getEmail(),
                                                                      customerToAdd.getPassword());
        assertThat(response).isEqualTo(desiredResponse);
    }

    @Test
    public void shouldReturnProductListFromSpecificBasket() throws Exception {
        Basket basketWeLookingFor = getBasket();

        when(mockedBasketService.getBasketById(4)).thenReturn(basketWeLookingFor);

        Set<Product> foundProductsFromBasket = testedObject.getProductsFromBasket(4);

        assertThat(foundProductsFromBasket).isNotNull();
        assertThat(foundProductsFromBasket.size()).isEqualTo(3);
    }

    //todo improve this
    @Test
    public void shouldAddProductToBasket() throws Exception {
        ResponseEntity<String> desiredResponse = new ResponseEntity<>("Product has been added to basket", HttpStatus.OK);

        doNothing().when(mockedBasketService).addProductsToBasket("testProduct", 1, 6);
        ResponseEntity<String> response = testedObject.addProductToBasket(6, "testProduct", 1);

        assertThat(response).isEqualTo(desiredResponse);
    }

    @Test
    public void shouldReturnTotalPriceOfProductsFromBasket() throws Exception {
        when(mockedBasketService.getTotalPrice(4)).thenReturn(30.0);

        Double totalPrice = testedObject.getTotalPriceForProductsFromBasket(4);

        assertThat(totalPrice).isEqualTo(30.0);
    }

    @Test
    public void shouldClearBasket() {
        ResponseEntity<String> desiredResponse = new ResponseEntity<>("Removed products from basket", HttpStatus.OK);

        doNothing().when(mockedBasketService).clear(4);

        ResponseEntity response = testedObject.clearBasket(4);

        assertThat(response).isEqualTo(desiredResponse);
    }

    private Basket getBasket() {
        Basket testBasket = new Basket();
        testBasket.setId(4);

        Set<Product> testProducts = new HashSet<>();
        testProducts.add(new Product("testProduct1", 10.0, ProductType.OTHER, testBasket));
        testProducts.add(new Product("testProduct2", 10.0, ProductType.FRUIT, testBasket));
        testProducts.add(new Product("testProduct3", 10.0, ProductType.VEGETABLE, testBasket));

        testBasket.setProductList(testProducts);

        return testBasket;
    }

    private List<Customer> getCustomerList() {
        List<Customer> testCustomerList = new ArrayList<>();
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        Customer c3 = new Customer();
        Customer c4 = new Customer();
        testCustomerList.add(c1);
        testCustomerList.add(c2);
        testCustomerList.add(c3);
        testCustomerList.add(c4);
        return testCustomerList;
    }
}
