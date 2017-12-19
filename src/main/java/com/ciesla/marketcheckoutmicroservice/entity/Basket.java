package com.ciesla.marketcheckoutmicroservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "basket_id")
    @JsonManagedReference
    private Set<Product> productList;

    public Basket() {
    }

}
