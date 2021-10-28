package com.revature.repository;

import java.util.List;

import com.revature.model.Customer;

public interface CustomerRepository {

	List<Customer> getAllCustomers();
	
	void insertCustomer(Customer c);
	Customer getCustomerByUsername(String username);
	//necessary?
	void update(Customer c);
	//necessary?
	void deleteCustomer(Customer c);
	
}
