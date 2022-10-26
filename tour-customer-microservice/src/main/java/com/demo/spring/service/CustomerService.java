package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Customer;
import com.demo.spring.repo.CustomerRepository;
/***
 * 
 * @author shubh-sinha
 * @project Tour-Management
 *
 */
@Service
public class CustomerService implements CustomerServiceDAO{
	
	@Autowired
	private CustomerRepository customerRepo;

	/***
	 * @param customer
	 * @method register customer
	 */
	@Override
	public String registerCustomer(Customer customer) {
		if(customerRepo.existsByUsername(customer.getUsername())) {
			return "Customer with username already exists";
		}
		else {
			customerRepo.save(customer);
			return "Customer Added Successfully";
		}
		
	}

	/***
	 * @param username
	 * @param password
	 * @method login
	 *
	 */
	@Override
	public boolean accessCustomer(String username, String password) {
		Optional<Customer> customer = customerRepo.findByUsername(username);
		if(customer.isPresent()) {
			if(customer.get().getUsername().equals(username)) {
			return true;
		}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	/***
	 * @method list customer
	 * @return list
	 */
	public List<Customer> listCustomer() {
		return customerRepo.findAll();
	}

	@Override
	/***
	 * @method find customer by username
	 */
	public Optional<Customer> findByUsername(String username) {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepo.findByUsername(username);
		return customer;
	}



}
