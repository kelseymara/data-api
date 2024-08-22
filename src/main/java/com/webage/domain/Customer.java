package com.webage.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CUSTOMERS")
public class Customer{
	// variables
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // id as primary key
	long id;
	
	@Column(name="CUSTOMER_NAME")
	String name;
	
	String password;

	String email;
	
	public Customer() {
		
	}
	public Customer(long id, String name, String email) {
		this.id=id;
		this.name=name;
		this.email=email;
	}
	// getters and setters
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}