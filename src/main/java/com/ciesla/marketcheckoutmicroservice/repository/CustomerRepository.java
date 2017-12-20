package com.ciesla.marketcheckoutmicroservice.repository;

import com.ciesla.marketcheckoutmicroservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findAll();
    Customer findCustomerById(Integer id);
}
