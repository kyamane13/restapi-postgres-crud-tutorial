package com.kyama.api.service.impl;

import com.kyama.api.dto.Customer;
import com.kyama.api.repository.CustomerMapper;
import com.kyama.api.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Customer register(Customer customer) {
        String formattedEmail = formatEmail(customer.getEmail());
        customer.setEmail(formattedEmail);

        mapper.insert(customer);
        return customer;
    }

    private String formatEmail(String email) {
        String[] separatedEmail = email.split("@");
        return separatedEmail[0] +"@" +separatedEmail[1].toLowerCase();
    }
}
