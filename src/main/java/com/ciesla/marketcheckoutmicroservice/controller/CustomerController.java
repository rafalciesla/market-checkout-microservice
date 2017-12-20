package com.ciesla.marketcheckoutmicroservice.controller;

import com.ciesla.marketcheckoutmicroservice.NotEnoughProductsException;
import com.ciesla.marketcheckoutmicroservice.ProductNotFoundException;
import com.ciesla.marketcheckoutmicroservice.entity.Basket;
import com.ciesla.marketcheckoutmicroservice.entity.Customer;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.service.CustomerService;
import com.ciesla.marketcheckoutmicroservice.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/customer/")
public class CustomerController {

    private BasketService basketService;

    private CustomerService customerService;

    @Autowired
    public CustomerController(BasketService basketService, CustomerService customerService) {
        this.basketService = basketService;
        this.customerService = customerService;
    }

    @GetMapping("find/all")
    public List<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer findCustomerById(@PathVariable("customerId") Integer customerId) {
        return customerService.findCustomerById(customerId);
    }

    @GetMapping("add/new/" + "{firstName}" + "/" + "{lastName}" + "/" + "{email}" + "/" + "{password}")
    public ResponseEntity<String> addNewCustomer(@PathVariable("firstName") String firstName,
                                         @PathVariable("lastName") String lastName,
                                         @PathVariable("email") String email,
                                         @PathVariable("password") String password) {
        Customer customerToAdd = new Customer(firstName, lastName, email, password);
        customerService.addNewCustomer(customerToAdd);
        return new ResponseEntity<>("New customer has been added", HttpStatus.CREATED);
    }

    @GetMapping("{basketId}" + "/basket")
    public Set<Product> getProductsFromBasket(@PathVariable("basketId") Integer basketId) {
        return basketService.getBasketById(basketId).getProductList();
    }

    @GetMapping("{basketId}" + "/basket/" + "/add/" + "{productName}" + "/" + "{quantity}")
    public ResponseEntity<String> addProductToBasket(@PathVariable("basketId") Integer basketId,
                                   @PathVariable("productName") String productName,
                                   @PathVariable("quantity") Integer quantity) {
        try {
            basketService.addProductsToBasket(productName, quantity, basketId);
        } catch (ProductNotFoundException | NotEnoughProductsException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Product has been added to basket", HttpStatus.OK);
    }

    @GetMapping("{basketId}" + "/basket/" +"/total")
    public Double getTotalPriceForProductsFromBasket(@PathVariable("basketId") Integer basketId) {
        return basketService.getTotalPrice(basketId);
    }

    @GetMapping("{basketId}" + "/basket/" +"/clear")
    public ResponseEntity<String> clearBasket(@PathVariable("basketId") Integer basketId) {
        basketService.clear(basketId);
        return new ResponseEntity<>("Removed products from basket", HttpStatus.OK);
    }
}
