package com.ciesla.marketcheckoutmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @GetMapping("/")
    public String mainPage() {
        return "hello";
    }

}
