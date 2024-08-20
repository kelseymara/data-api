package com.webage;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webage.repository.CustomersRepository;

@RestController
@RequestMapping("/api")
public class CustomerController{

	@Autowired CustomersRepository repo;
	
	// Default
	@GetMapping
	public String defaultAPI() {
			return "API is working";
	}
	

	// Return list of customers /customers
	@GetMapping("/customers")
	public Iterable<Customer> getAll(){
		return repo.findAll();
	}
	
	// Return customer based on ID
	@GetMapping("/customers/{customerId}")
	public Optional <Customer>getCustomerById(@PathVariable("customerId") long id){
		return repo.findById(id);
	}
}