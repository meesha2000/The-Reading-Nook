package com.mystore.onlinebookstore.The.Reading.Nook.Services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.CustomerRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class CustomerService {

	
	private final CustomerRepository customerRepository;
	
	@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    } 
	 
	public Customer registerCustomer(@Valid Customer customer) {
        validateCustomerData(customer);
        return customerRepository.save(customer);
    }

	 public Optional<Customer> findCustomerByUsername(String username) {
	        return customerRepository.findByUsername(username);
	    }

	 public Optional<Customer> updateCustomerProfile(String username, Customer updatedCustomer) {
	        
		 Optional<Customer> existingCustomer = findCustomerByUsername(username);
	        if (existingCustomer.isPresent()) {
	            Customer customerToUpdate = existingCustomer.get();
	            
	            if (updatedCustomer.getFirstName() != null) {
	                customerToUpdate.setFirstName(updatedCustomer.getFirstName());
	            }
	            if (updatedCustomer.getLastName() != null) {
	                customerToUpdate.setLastName(updatedCustomer.getLastName());
	            }
	            if (updatedCustomer.getEmail() != null) {
	                customerToUpdate.setEmail(updatedCustomer.getEmail());
	            }
	            
	            validateCustomerData(customerToUpdate);
	            
	            return Optional.of(customerRepository.save(customerToUpdate));
	        } else {
	            return Optional.empty(); // Customer not found
	        }
	    }

	    public void validateCustomerData(@Valid Customer customer) {
	        // Implement data validation according to your company standards.
	        // You can use validation annotations and/or custom validation logic.
	        // For example, you can use javax.validation.constraints for validation.
	    	
	    	
	    	if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
	            throw new IllegalArgumentException("First name is required.");
	        }
	        if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
	            throw new IllegalArgumentException("Last name is required.");
	        }
	        // Add more validation rules here as needed.
	    }

}
