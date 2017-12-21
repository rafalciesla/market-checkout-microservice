package com.ciesla.marketcheckoutmicroservice.service;

import com.ciesla.marketcheckoutmicroservice.entity.Customer;
import com.ciesla.marketcheckoutmicroservice.repository.BasketRepository;
import com.ciesla.marketcheckoutmicroservice.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private CustomerService testedObject;

    @Mock
    private CustomerRepository mockedCustomerRepository;

    @Mock
    private BasketRepository mockedBasketRepository;

    @Before
    public void setUp() throws Exception {
        testedObject = new CustomerService(mockedCustomerRepository, mockedBasketRepository);
    }

    @Test
    public void shouldReturnAllCustomers() throws Exception {
        when(mockedCustomerRepository.findAll()).thenReturn(getCustomers());

        List<Customer> customerList = testedObject.findAllCustomers();

        assertThat(customerList).isNotNull();
        assertThat(customerList).isNotEmpty();
        assertThat(customerList.size()).isEqualTo(3);
    }

    @Test
    public void shouldReturnSpecificCustomerWhenCustomerIdPassed() throws Exception {
        Customer customerWeLookingFor = new Customer();
        customerWeLookingFor.setId(99);

        when(mockedCustomerRepository.findCustomerById(99)).thenReturn(customerWeLookingFor);

        Customer foundCustomer = testedObject.findCustomerById(99);

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getId()).isEqualTo(99);
        assertThat(customerWeLookingFor).isEqualTo(foundCustomer);
    }

    @Test
    public void shouldAddNewCustomer() throws Exception {
        Customer customerToAdd = new Customer();

        when(mockedCustomerRepository.save(customerToAdd)).thenReturn(customerToAdd);

        testedObject.addNewCustomer(customerToAdd);

        verify(mockedCustomerRepository, times(1)).save(customerToAdd);
    }

    private List<Customer> getCustomers() {
        List testCustomers = new ArrayList();
        testCustomers.add(new Customer());
        testCustomers.add(new Customer());
        testCustomers.add(new Customer());
        return testCustomers;
    }
}
