package com.kyama.api.controller;

import com.kyama.api.dto.Customer;
import com.kyama.api.service.CustomerService;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @PostMapping
    public Customer post(@Validated @RequestBody Customer customer, Errors errors){
        if(errors.hasErrors()){
            throw new RuntimeException((Throwable) errors);
        }
        return customerService.register(customer);
    }
}
