package com.mystore.onlinebookstore.The.Reading.Nook.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	
	@InjectMocks
    private CustomerService customerService;

	@Mock
    private CustomerRepository customerRepository;

    //private Validator validator;

    @BeforeEach
    public void setUp() {
        
        customerService = new CustomerService(customerRepository);

        // Initialize the Validator
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
    }

    @Test
    public void testRegisterCustomer_ValidCustomer() {
    	
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer registeredCustomer = customerService.registerCustomer(customer);

        assertThat(registeredCustomer).isNotNull();
        assertThat(registeredCustomer.getFirstName()).isEqualTo("John");
        assertThat(registeredCustomer.getLastName()).isEqualTo("Doe");
        assertThat(registeredCustomer.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testRegisterCustomer_InvalidCustomer() {
    	
        Customer customer = new Customer(); // An invalid customer with missing fields

        // No need to mock customerRepository.save since it shouldn't be called

        // Validate the customer using the service's private method
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.validateCustomerData(customer);
        });
    }

    @Test
    public void testUpdateCustomerProfile_CustomerNotFound() {
    	
        String username = "nonexistent_user";
        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Updated");
        updatedCustomer.setLastName("Name");

        when(customerRepository.findByUsername(username)).thenReturn(Optional.empty());

        Optional<Customer> result = customerService.updateCustomerProfile(username, updatedCustomer);

        assertThat(result).isEmpty();
    }

    @Test
    public void testUpdateCustomerProfile_ValidCustomer() {
    	
        String username = "existing_user";
        Customer existingCustomer = new Customer();
        existingCustomer.setUsername(username);
        existingCustomer.setFirstName("John");
        existingCustomer.setLastName("Doe");
        existingCustomer.setEmail("john.doe@example.com");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Updated");
        updatedCustomer.setLastName("Name");

        when(customerRepository.findByUsername(username)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        Optional<Customer> result = customerService.updateCustomerProfile(username, updatedCustomer);

        assertThat(result).isPresent();
        assertThat(result.get().getFirstName()).isEqualTo("Updated");
        assertThat(result.get().getLastName()).isEqualTo("Name");
        assertThat(result.get().getEmail()).isEqualTo("john.doe@example.com");
    }
}
