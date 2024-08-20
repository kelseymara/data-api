package com.webage;


import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	// Create customer
	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer){
		// Validate Input
		if(newCustomer.getName()==null 
				|| newCustomer.getEmail()==null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer = repo.save(newCustomer);
		
		URI location = 
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{customerId}")
				.buildAndExpand(newCustomer.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<?> putCustomer(
			@RequestBody Customer customer, 
			@PathVariable long id)
	{
		if(customer.getId()!=id
				|| customer.getName() == null
				|| customer.getEmail()==null) {
			return ResponseEntity.badRequest().build();
		}
		
		repo.save(customer);
		return ResponseEntity.ok().build();
	}
	
	// Delete
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<?>deleteCustomer(@PathVariable("customerId") long id){
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}