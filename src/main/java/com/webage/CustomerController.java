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
import org.springframework.web.bind.annotation.CrossOrigin;


import com.webage.repository.CustomersRepository;
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin
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
	@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin
	@GetMapping("/customers")
	public Iterable<Customer> getAll(){
		return repo.findAll();
	}
	
	// Return customer based on ID
	@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin
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

	@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin
	@PostMapping("/react/customers")
public ResponseEntity<Customer> addReactCustomer(@RequestBody Customer newCustomer) {
    // Validate Input
    if (newCustomer.getName() == null || newCustomer.getEmail() == null) {
        return ResponseEntity.badRequest().build();
    }

    // Save the customer
    Customer savedCustomer = repo.save(newCustomer);
    
    // Build the URI for the created resource
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{customerId}")
        .buildAndExpand(savedCustomer.getId())
        .toUri();

    // Return the response with the created customer and location
    return ResponseEntity.created(location).body(savedCustomer);
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
	
	@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin
@PutMapping("/react/customers/{id}")
public ResponseEntity<Customer> putReactCustomer(
        @RequestBody Customer customer, 
        @PathVariable long id) {
    // Validate Input
    if (customer.getId() != id || customer.getName() == null || customer.getEmail() == null) {
        return ResponseEntity.badRequest().build();
    }

    // Check if the customer exists
    if (!repo.existsById(id)) {
        return ResponseEntity.notFound().build();
    }

    // Update the customer
    customer.setId(id); // Ensure the ID is set correctly
    Customer updatedCustomer = repo.save(customer);

    // Return the response with the updated customer
    return ResponseEntity.ok(updatedCustomer);
}
	// Delete
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<?>deleteCustomer(@PathVariable("customerId") long id){
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}