package com.mystore.onlinebookstore.The.Reading.Nook.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mystore.onlinebookstore.The.Reading.Nook.Controllers.CustomerController;
import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;
import com.mystore.onlinebookstore.The.Reading.Nook.Services.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	@InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;
    
//    @Test
//    public void testRegisterCustomer() {
//    	
//    	Customer customer = new Customer();
//		customer.setUsername("myUser");
//		customer.setEmail("Myuser@gmail.com");
//		customer.setFirstName("myuser");
//		customer.setLastName("exists");
//		
//		when(customerService.registerCustomer(customer)).thenReturn(customer);
//		
//		ResponseEntity<Optional<Customer>> response = customerController.registerCustomer(customer);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(customer, response.getBody().get());
//    }
    
    @Test
    public void testGetCustomerProfile() {
    	
        String username = "test_user";
        Customer customer = new Customer();
        customer.setUsername(username);

        when(customerService.findCustomerByUsername(username)).thenReturn(Optional.of(customer));

        ResponseEntity<Optional<Customer>> response = customerController.getCustomerProfile(username);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
        assertEquals(username, response.getBody().get().getUsername());
    }
    
    @Test
    public void testUpdateCustomerProfile() {
    	
        Customer customer = new Customer();
        customer.setUsername("test_user");
        customer.setEmail("test@example.com");
        customer.setFirstName("test1");
        customer.setLastName("last");

        when(customerService.updateCustomerProfile(any(), any())).thenReturn(Optional.of(customer));

        ResponseEntity<Optional<Customer>> response = customerController.updateCustomerProfile(customer);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isPresent());
        assertEquals("test_user", response.getBody().get().getUsername());
    }

}
