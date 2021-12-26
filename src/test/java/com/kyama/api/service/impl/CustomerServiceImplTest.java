package com.kyama.api.service.impl;

import com.kyama.api.dto.Customer;
import com.kyama.api.repository.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("CREATE TEST: Check if registeration succeeded.")
    @Test
    public void testRegister() {
        Customer customer = new Customer();
        customer.setId("100");
        customer.setUsername("user100");
        customer.setEmail("test.user.100@EXAMPLE.com");
        customer.setPhoneNumber("01234567890");
        customer.setPostCode("1234567");

        when(mapper.insert(Mockito.any(Customer.class))).thenReturn(1);
        Customer actual = service.register(customer);
        assertEquals(customer.getId(), actual.getId());
        assertEquals(customer.getUsername(), actual.getUsername());
        assertEquals("test.user.100@example.com", actual.getEmail());
        assertEquals(customer.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(customer.getPostCode(), actual.getPostCode());
        Mockito.verify(mapper, Mockito.times(1)).insert(customer);
    }
}