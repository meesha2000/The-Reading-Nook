package com.mystore.onlinebookstore.The.Reading.Nook.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;
import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Vendor;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.CustomerRepository;
import com.mystore.onlinebookstore.The.Reading.Nook.Repositories.VendorRepository;

@Service
public class LoginService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VendorRepository vendorRepository;


    public boolean loginCustomer(String username, String password) {
    	
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            // Check if the provided password matches the stored password (consider using password hashing)
            return password.equals(customer.getPassword());
        }
        return false; // User not found
    }
    
    public boolean loginVendor(String username, String password) {
    	
        Optional<Vendor> vendorOptional = vendorRepository.findByUsername(username);

        if (vendorOptional.isPresent()) {
            Vendor vendor = vendorOptional.get();
            // Check if the provided password matches the stored password (consider using password hashing)
            return password.equals(vendor.getPassword());
        }

        return false; // User not found
    }
}
