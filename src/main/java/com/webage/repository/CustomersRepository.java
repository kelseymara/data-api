package com.webage.repository;

import org.springframework.data.repository.CrudRepository;

import com.webage.domain.Customer;

//  extends CrudRepository to provide basic CRUD operations for Customer entities, where the entity's ID is of type Long

public interface CustomersRepository extends CrudRepository<Customer, Long>{
	
}