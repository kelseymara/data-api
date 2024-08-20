package com.webage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
	public void testGetAll()
	{
		
	}
	
	@Test
	@Disabled
	public void testGetById()
	{
		
	}
	
}
