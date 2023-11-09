package com.mystore.onlinebookstore.The.Reading.Nook.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;
import com.mystore.onlinebookstore.The.Reading.Nook.Services.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
    private CustomerService customerService;

    @GetMapping("/{username}")
    public ResponseEntity<Optional<Customer>> getCustomerProfile(@PathVariable String username) {
    	Optional<Customer> customer = customerService.findCustomerByUsername(username);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<Customer>> updateCustomerProfile(@RequestBody @Valid Customer customer) {
        Optional<Customer> updatedCustomer = customerService.updateCustomerProfile(customer.getUsername(),customer);
        return ResponseEntity.ok(updatedCustomer);
    }

}
