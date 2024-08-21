package com.webage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.webage.api.CustomerController;
import com.webage.domain.Customer;

@SpringBootTest
class StudentApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testDefault() {
		var controller = new CustomerController();
		String result = controller.defaultAPI();
		
		assertNotNull(result);
		assertTrue(result.contains("API is working"));
	}
	
	@Test
	@Disabled
	public void testGettersAndSetters() 
	{
		Customer customer = new Customer();
		customer.setName("Kelsey");
		customer.setId(1);
		customer.setEmail("kelsey@mail.com");
		
		assertEquals("Kelsey",customer.getName());
		assertEquals(1,customer.getId());
		assertEquals("kelsey@mail.com",customer.getEmail());
		
	}
	
	@Test
	@Disabled
	public void testGetAll()
	{
		
	}
	
	@Test
	@Disabled
	public void testGetById()
	{
		
	}
	
}
