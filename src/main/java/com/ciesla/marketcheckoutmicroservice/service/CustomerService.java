package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.entity.Basket;
import com.ciesla.marketcheckoutmicroservice.entity.Customer;
import com.ciesla.marketcheckoutmicroservice.repository.BasketRepository;
import com.ciesla.marketcheckoutmicroservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    private BasketRepository basketRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BasketRepository basketRepository) {
        this.customerRepository = customerRepository;
        this.basketRepository = basketRepository;
    }

    @Transactional
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer findCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }

    @Transactional
    public void addNewCustomer(Customer customer) {
        Basket basket = new Basket();
        basketRepository.save(basket);
        customer.setBasket(basket);
        customerRepository.save(customer);
    }

    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
