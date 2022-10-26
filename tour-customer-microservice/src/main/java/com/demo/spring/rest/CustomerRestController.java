package com.demo.spring.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Customer;
import com.demo.spring.service.CustomerServiceDAO;
/***
 * 
 * @author shubh-sinha
 * @project tour-management
 *
 */
@RestController
public class CustomerRestController {
	
	@Autowired
	private CustomerServiceDAO customerService;
	
	/***
	 * @Method Register customer
	 * @param customer
	 * @return- response
	 */
	@PostMapping(value="/customer/add", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
		String result = customerService.registerCustomer(customer);
		return ResponseEntity.ok("{\"status\":\""+result+"\"}");
	}
	
	/***
	 * @methid Verify Customer
	 * @param username
	 * @param password
	 * @return response
	 */
	@GetMapping(value="/customer/{username}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> accessCustomer(@PathVariable("username") String username, @PathVariable("password") String password){
		boolean result = customerService.accessCustomer(username, password);
		if(result == true) {
			return ResponseEntity.ok("Customer");
		}
		else {
			return ResponseEntity.status(200).body("{\"status\":\"Customer either not registered or found\"}");
		}
	}
	
	/**
	 * @method View Customer
	 * @return list
	 */
	@GetMapping(value="customer/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> viewAllCustomer(){
		return customerService.listCustomer();
	}
	
	/***
	 * 
	 * @param username
	 * @param password
	 * @return customer
	 */
	@GetMapping(value="/customer/get/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCustomer(@PathVariable("username") String username){
		Optional<Customer> result = customerService.findByUsername(username);
		if(result.isEmpty()) {
			return ResponseEntity.status(200).body("{\"status\":\"Cannot be null\"}");
		}
		else {
			if(result.get().getUsername().equals(username)) {
				return ResponseEntity.ok(result);
			}
			else {
				return ResponseEntity.status(200).body("{\"status\":\"Customer either not registered or found\"}");
			}
		}

	}

}
