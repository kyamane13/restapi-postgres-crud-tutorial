package com.kyama.api.service;

import com.kyama.api.dto.Customer;

public interface CustomerService {
    /**
     * Register a new customer information.
     *
     * @param customer customer object which we want to register
     * @return customer object registered
     */
    Customer register(Customer customer);
}
