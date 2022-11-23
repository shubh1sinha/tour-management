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
		ResponseEntity<List<Tour>> tours = rt.exchange("http://TOUR-ADMIN-MICROSERVICE/tour/list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Tour>>() {
				});
		return tours;
		
	}
	
	@GetMapping("/customer/list")
	public ResponseEntity<List<Customer>> getCustomerList(){
		ResponseEntity<List<Customer>> customers = rt.exchange("http://TOUR-CUSTOMER-MICROSERVICE/customer/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Customer>>() {
				});
		return customers;
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addTour(@RequestBody Tour tour){
		ResponseEntity<String> response = rt.postForEntity("http://TOUR-ADMIN-MICROSERVICE/tour/add", tour, String.class);
		return response;
	}
	
	@PostMapping("/customer/registration")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
		ResponseEntity<String> response = rt.postForEntity("http://TOUR-CUSTOMER-MICROSERVICE/customer/add", customer, String.class);
		return response;
	}
	
	@GetMapping("/customer/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username){
		ResponseEntity<Customer> customer = rt.getForEntity("http://TOUR-CUSTOMER-MICROSERVICE/customer/get/"+username, Customer.class);
		return customer;
	}
	
	@GetMapping("/customer/get/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
		ResponseEntity<Customer> customer = rt.getForEntity("http://TOUR-CUSTOMER-MICROSERVICE/customer/"+id, Customer.class);
		return customer;
	}
	

	@PostMapping(value = "/login")
	public ResponseEntity<String> loginCheck(@RequestBody Admin admin) {
		if(admin.getUsername().equals("admin")) {
			ResponseEntity<String> resp1 = rt.postForEntity("http://TOUR-ADMIN-MICROSERVICE/admin/login/", admin, String.class);
			return resp1;
		}
		else {
			ResponseEntity<String> resp2 = rt.getForEntity(
					"http://TOUR-CUSTOMER-MICROSERVICE/customer/" + admin.getUsername() + "/" + admin.getPassword(), String.class);
			return resp2;
		}
	}
	
	@PostMapping(value = "/booking")
	public ResponseEntity<String> addBooking(@RequestBody Booking booking, @RequestParam("tourId") int tourId, @RequestParam("customerId") int customerId) {
		booking.setCustomerId(customerId);
		ResponseEntity<String> response = rt.postForEntity("http://TOUR-ADMIN-MICROSERVICE/booking/add/" +tourId , booking,
				String.class);
		return response;
		
		
	}
	
	@GetMapping(value = "/list/booking")
	public ResponseEntity<List<Booking>> bookedTours(@RequestParam("customerId") int customerId) {
		ResponseEntity<List<Booking>> response = rt.exchange("http://TOUR-ADMIN-MICROSERVICE/booking/list/" + customerId,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
				});
		return response;
	}
	
	@GetMapping(value = "/bookings")
	public ResponseEntity<List<Booking>> bookeingList() {
		ResponseEntity<List<Booking>> response = rt.exchange("http://TOUR-ADMIN-MICROSERVICE/booking/list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Booking>>() {
				});
		return response;
	}
	
	
	/***
	 * 
	 * @param bookingId
	 * @return
	 * @description Once booking is placed 
	 * 	it is send to payment page and then
	 *  if payment is done response will be directly
	 *  send to booking page and booking is added
	 *  else it will be false with pending status
	 *  
	 */
	@PostMapping(value= "/verification")
	public ResponseEntity<String> paymentVerification(@RequestParam("bookingId") int bookingId){
		ResponseEntity<String> response = rt.getForEntity("http://TOUR-ADMIN-MICROSERVICE/booking/update/" + bookingId,
				String.class);
		if(response.equals(true)) {
			//sending it to customer bookings
			return ResponseEntity.ok("Bookings Made Successfully");
			
		}
		else {
			//sending it to pendings
			return ResponseEntity.status(404).body("Still Pending for payment");
		}
		
	}
	
	
	
	
	
	
}
