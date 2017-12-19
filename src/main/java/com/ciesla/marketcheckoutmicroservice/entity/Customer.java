package com.ciesla.marketcheckoutmicroservice.entity;

import com.ciesla.marketcheckoutmicroservice.repository.BasketRepository;
import com.ciesla.marketcheckoutmicroservice.service.BasketService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Random;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne
    private Basket basket;

    public Customer() {

    }

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
