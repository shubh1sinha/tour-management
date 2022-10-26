package com.demo.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Admin;
import com.demo.spring.entity.Booking;
import com.demo.spring.entity.Tour;
import com.demo.spring.service.AdminServiceDAO;

/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Rest Controller
 *
 */
@RestController
public class AdminRestController {
	
	@Autowired
	private AdminServiceDAO adminService;
	
	/***
	 * @Method AccessAdmin
	 * @param admin
	 * @return true & false
	 */
	@PostMapping(value = "/admin/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> accessAdmin(@RequestBody Admin admin) {
		
		boolean result = adminService.accessAdmin(admin.getUsername(), admin.getPassword());
		if(result == true) {
			return ResponseEntity.ok("Admin");
		}
		else {
			return ResponseEntity.status(200).body("{\"status\":\"Admin either not registered or found\"}"); 
		}
	}
	
	/***
	 * @method Add Tour
	 * @param tour
	 * @return Added successfully
	 */
	@PostMapping(value="/tour/add", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addTour(@RequestBody Tour tour){
		adminService.addTour(tour);
		return ResponseEntity.ok("{\"status\":\"Tour Added Successfully\"}");
	}
	
	/***
	 * @method Add Booking
	 * @param booking
	 * @return Added successfully
	 */
	@PostMapping(value="/booking/add/{tourId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addBooking(@RequestBody Booking booking,@PathVariable("tourId") int tourId){
		 adminService.addBooking(booking, tourId);
		return ResponseEntity.ok("{\"status\":\"Booking Added Successfully\"}");
	}
	
	/***
	 * 
	 * @param bookingId
	 * @return Update booking
	 */
	@GetMapping(value="/booking/update/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateBooking(@PathVariable("bookingId") int bookingId){
		String status = adminService.updateBookingStatus(bookingId);
		if(status.contains("No")) {
			return ResponseEntity.status(400).body("{\"status\":\"Booking Id Not Found\"}"); 
		}
		return ResponseEntity.ok("{\"status\":\"Payment Done Successfully\"}");
	}
	
	/***
	 * 
	 * @return List of tours
	 */
	@GetMapping(value="/tour/list", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Tour> viewAllTours(){
		return adminService.listAllTours();
	}
	
	/***
	 * 
	 * @param customerId
	 * @return booking list of customerId
	 */
	@GetMapping(value="/booking/list/{customerId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> viewAllCustomerBookings(@PathVariable("customerId") int customerId){
		return adminService.listAllBooking(customerId);
	}
	
	/***
	 * 
	 * @return list of booking
	 */
	@GetMapping(value="booking/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> viewAllBookings(){
		return adminService.listBookings();
	}
	

}
