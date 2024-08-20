package com.webage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	@Disabled
	public void testGetList() {
		Customer[] customers = template.getForObject("/customers", Customer[].class);
		
		assertNotNull(customers);
		
	}
	
	
}