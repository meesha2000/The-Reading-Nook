package com.mystore.onlinebookstore.The.Reading.Nook.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.onlinebookstore.The.Reading.Nook.DTOs.CustomerRegistrationDto;
import com.mystore.onlinebookstore.The.Reading.Nook.DTOs.VendorRegistrationDto;
import com.mystore.onlinebookstore.The.Reading.Nook.Services.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegisterationController {

	@Autowired
	RegistrationService registrationService;

    
    @PostMapping("/customer")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrationDto registrationDto) {
    	registrationService.registerNewCustomer(registrationDto);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/vendor")
    public ResponseEntity<String> registerVendor(@RequestBody VendorRegistrationDto registrationDto) {
    	registrationService.registerNewVendor(registrationDto);
        return ResponseEntity.ok("Vendor registered successfully");
    }
    
    // Add similar methods for admin and author/publisher registration

}
