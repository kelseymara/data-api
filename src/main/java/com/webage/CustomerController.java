package com.webage;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController{
	// create array list that stores customers
	ArrayList<Customer> customersList = new ArrayList<Customer>();
	
	// hard coded data
	public CustomerController() {
		customersList.add(new Customer(1,"Kelsey","kelsey@mail.com"));
		customersList.add(new Customer(2,"Pom","pom@mail.com"));
		customersList.add(new Customer(3,"Cinna","cinna@mail.com"));
	}
	
	// Default
	@GetMapping
	public String defaultAPI() {
			return "API is working";
	}
	

	// Return list of customers /customers
	@GetMapping("/customers")
	public ArrayList<Customer> getAllCustomers() 
	{
		return customersList;
	}
	
	// Return customer based on ID
	@GetMapping("/customers/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") long id){
		for (Customer c:customersList) {
			if (c.getId()==id)
				return c;
		}
		return null;
	}
}