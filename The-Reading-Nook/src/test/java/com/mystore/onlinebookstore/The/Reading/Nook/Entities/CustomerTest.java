package com.mystore.onlinebookstore.The.Reading.Nook.Entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerTest {

	@Test
	public void testGettersAndSetters() {
		
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setUsername("john_doe");
		customer.setEmail("john@example.com");
		customer.setPassword("password");
		customer.setFirstName("John");
		customer.setLastName("Doe");

		assertEquals(1L, customer.getId());
		assertEquals("john_doe", customer.getUsername());
		assertEquals("john@example.com", customer.getEmail());
		assertEquals("password", customer.getPassword());
		assertEquals("John", customer.getFirstName());
		assertEquals("Doe", customer.getLastName());
	}

}
