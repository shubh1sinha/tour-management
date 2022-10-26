package com.demo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.entity.Admin;
import com.demo.spring.entity.Booking;
import com.demo.spring.entity.Customer;
import com.demo.spring.entity.Tour;

@Controller
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(value = "/tour")
public class TourReactController {

	@Autowired
	private RestTemplate rt;
	
	@GetMapping("/list")
	public ResponseEntity<List<Tour>> getTourList(){
		ResponseEntity<List<Tour>> tours = rt.exchange("http://localhost:9191/tour/list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Tour>>() {
				});
		return tours;
		
	}
	
	@GetMapping("/customer/list")
	public ResponseEntity<List<Customer>> getCustomerList(){
		ResponseEntity<List<Customer>> customers = rt.exchange("http://localhost:9192/customer/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Customer>>() {
				});
		return customers;
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addTour(@RequestBody Tour tour){
		ResponseEntity<String> response = rt.postForEntity("http://localhost:9191/tour/add", tour, String.class);
		return response;
	}
	
	@PostMapping("/customer/registration")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
		ResponseEntity<String> response = rt.postForEntity("http://localhost:9192/customer/add", customer, String.class);
		return response;
	}
	
	@GetMapping("/customer/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username){
		ResponseEntity<Customer> customer = rt.getForEntity("http://localhost:9192/customer/get/"+username, Customer.class);
		return customer;
	}
	
	@GetMapping("/customer/get/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
		ResponseEntity<Customer> customer = rt.getForEntity("http://localhost:9192/customer/"+id, Customer.class);
		return customer;
	}
	

	@PostMapping(value = "/login")
	public ResponseEntity<String> loginCheck(@RequestBody Admin admin) {
		if(admin.getUsername().equals("admin")) {
			ResponseEntity<String> resp1 = rt.postForEntity("http://localhost:9191/admin/login/", admin, String.class);
			return resp1;
		}
		else {
			ResponseEntity<String> resp2 = rt.getForEntity(
					"http://localhost:9192/customer/" + admin.getUsername() + "/" + admin.getPassword(), String.class);
			return resp2;
		}
	}
	
	@PostMapping(value = "/booking")
	public ResponseEntity<String> addBooking(@RequestBody Booking booking, @RequestParam("tourId") int tourId, @RequestParam("customerId") int customerId) {
		booking.setCustomerId(customerId);
		ResponseEntity<String> response = rt.postForEntity("http://localhost:9191/booking/add/" +tourId , booking,
				String.class);
		return response;
		
		
	}
	
	@GetMapping(value = "/list/booking")
	public ResponseEntity<List<Booking>> bookedTours(@RequestParam("customerId") int customerId) {
		ResponseEntity<List<Booking>> response = rt.exchange("http://localhost:9191/booking/list/" + customerId,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
				});
		return response;
	}
	
	@GetMapping(value = "/bookings")
	public ResponseEntity<List<Booking>> bookeingList() {
		ResponseEntity<List<Booking>> response = rt.exchange("http://localhost:9191/booking/list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Booking>>() {
				});
		return response;
	}
	
	
	
	
	
	
}
