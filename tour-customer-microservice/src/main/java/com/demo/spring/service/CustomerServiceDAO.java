package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import com.demo.spring.entity.Customer;

/***
 * 
 * @author shubh-sinha
 * @project tour-management
 *
 */
public interface CustomerServiceDAO {
	
	public String registerCustomer(Customer customer);
	
	public boolean accessCustomer(String username, String password);
	
	public List<Customer> listCustomer();
	
	public Optional<Customer> findByUsername(String username);

}
