package com.ciesla.marketcheckoutmicroservice.controller;

import com.ciesla.marketcheckoutmicroservice.NotEnoughProductsException;
import com.ciesla.marketcheckoutmicroservice.ProductNotFoundException;
import com.ciesla.marketcheckoutmicroservice.entity.Customer;
import com.ciesla.marketcheckoutmicroservice.entity.Product;
import com.ciesla.marketcheckoutmicroservice.service.CustomerService;
import com.ciesla.marketcheckoutmicroservice.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("{basketId}" + "/basket")
    public Set<Product> getProductsFromBasket(@PathVariable("basketId") Integer basketId) {
        return basketService.getBasketById(basketId).getProductList();
    }

    @GetMapping("{basketId}" + "/basket/" + "/add/" + "{productName}" + "/" + "{quantity}")
    public void addProductToBasket(@PathVariable("basketId") Integer basketId,
                                   @PathVariable("productName") String productName,
                                   @PathVariable("quantity") Integer quantity) {
        try {
            basketService.addProductToBasket(productName, quantity, basketId);
        } catch (ProductNotFoundException | NotEnoughProductsException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("{basketId}" + "/basket/" +"/total")
    public Double getTotalPriceForProductsFromBasket(@PathVariable("basketId") Integer basketId) {
        Set<Product> productList = basketService.getBasketById(basketId).getProductList();
        return basketService.getTotalPrice(productList);
    }

    @GetMapping("{basketId}" + "/basket/" +"/clear")
    public void clearBasket(@PathVariable("basketId") Integer basketId) {
        basketService.clear(basketId);
    }

    @GetMapping("add/new/" + "{firstName}" + "/" + "{lastName}" + "/" + "{email}" + "/" + "{password}")
    public void addNewCustomer(@PathVariable("firstName") String firstName,
                               @PathVariable("lastName") String lastName,
                               @PathVariable("email") String email,
                               @PathVariable("password") String password) {
        customerService.addNewCustomer(firstName, lastName, email, password);
    }
}
