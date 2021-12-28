package com.kyama.api.controller;

import com.kyama.api.dto.Customer;
import com.kyama.api.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("CREATE TEST: Check if a new customer is registered and returned.")
    @Test
    public void testPost(){
        Customer customer=new Customer();
        customer.setId("100");
        customer.setUsername("user100");
        customer.setEmail("test.user.100@example.com");
        customer.setPhoneNumber("01234567890");
        customer.setPostCode("1234567");

        when(customerService.register(customer)).thenReturn(customer);

        Errors errors=mock(Errors.class);
        Customer response = controller.post(customer,errors);

        assertEquals(customer,response);
    }

    @DisplayName("CREATE TEST: Check if controller throws exception when some fields are invalid.")
    @Test
    public void testPostAbnormal(){
        Customer customer=new Customer();
        customer.setId("100");
        customer.setPhoneNumber("01234567890");
        customer.setPostCode("1234567");

        Errors errors=mock(Errors.class);
        when(errors.hasErrors()).thenReturn(true);
        assertThrows(RuntimeException.class,()-> controller.post(customer,errors));
    }
}