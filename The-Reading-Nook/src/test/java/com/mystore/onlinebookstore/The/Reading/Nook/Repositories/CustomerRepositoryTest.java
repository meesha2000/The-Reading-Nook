package com.mystore.onlinebookstore.The.Reading.Nook.Repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.mystore.onlinebookstore.The.Reading.Nook.Entities.Customer;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
	
	@Mock
    private CustomerRepository customerRepository;
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	void test() {
		
	}
	@Test
    public void testSaveCustomer() {
		
        Customer customer = new Customer();
        
        customer.setUsername("test_user");
        customer.setEmail("test@example.com");
        customer.setFirstName("test1");
        customer.setLastName("last");
        
        when(customerRepository.findByUsername("test_user")).thenReturn(Optional.of(customer));

        Customer savedCustomer = entityManager.persistAndFlush(customer);
        Optional<Customer> foundCustomer = customerRepository.findByUsername("test_user");

        assertThat(foundCustomer).isEqualTo(Optional.of(savedCustomer));
    }

	
	

}
