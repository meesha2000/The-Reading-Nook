package com.mystore.onlinebookstore.The.Reading.Nook.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.onlinebookstore.The.Reading.Nook.DTOs.CustomerRegistrationDto;
import com.mystore.onlinebookstore.The.Reading.Nook.DTOs.VendorRegistrationDto;
import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.CustomerRepository;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.VendorRepository;

@Service
public class RegistrationService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	
	public Customer registerNewCustomer(CustomerRegistrationDto registrationDto) {
		
		Customer customer = new Customer();
        // Set fields from registrationDto to customer
		customer.setPassword(registrationDto.getPassword());
		customer.setUsername(registrationDto.getUsername());
		customer.setMobile(registrationDto.getMobile());
		customer.setAddress(registrationDto.getAddress());
		customer.setDateOfBirth(registrationDto.getDateOfBirth());
		customer.setEmail(registrationDto.getEmail());
        customer.setFirstName(registrationDto.getFirstName());
        customer.setLastName(registrationDto.getLastName());
        
        // Set other fields...

        // Save to database
        return customerRepository.save(customer);
		
		
	}

	public void registerNewVendor(VendorRegistrationDto registrationDto) {
		// TODO Auto-generated method stub
		
	}

	
}
