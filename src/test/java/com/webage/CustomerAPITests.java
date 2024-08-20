package com.webage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CustomerAPITests{
	
	@Autowired TestRestTemplate template;
	
	@Test
	public void testGetList() {
		Customer[] customers = template.getForObject("/api/customers", Customer[].class);
		
		assertNotNull(customers);
		assertNotNull(customers[0]);
		assertNotNull(customers[0].getId());
		assertTrue(customers.length>0);
		
	}
	
	@Test
	public void testGet() {
		Customer customer = template.getForObject("/api/customers/{customerId}", Customer.class,1);
		
		assertNotNull(customer);
		assertNotNull(customer.getId());
	}
	
	@Test
	public void testCreate() {
		Customer customer = new Customer();
		customer.setName("Test");
		customer.setEmail("test@mail.com");
		
		URI location = template.postForLocation("/api/customers", customer,Customer.class);
		assertNotNull(location);
		
		customer= template.getForObject(location, Customer.class);
		assertNotNull(customer);
		assertNotNull(customer.getId());
		assertEquals("Test", customer.getName());
		assertEquals("test@mail.com",customer.getEmail());
	}
	
	@Test
	@Disabled
	public void testPut()
	{
		String path = "/api/customers/5";
		String newValue = "NewValue";
		
		Customer customer = template.getForObject(path, Customer.class);
		
		customer.setName(newValue);
		template.put(path, customer);
		
		customer = template.getForObject(path, Customer.class);
		
		assertEquals(newValue, customer.getName());
		
		
	}
	
}