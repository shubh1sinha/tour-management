package com.demo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.entity.Admin;
import com.demo.spring.entity.Booking;
import com.demo.spring.entity.Customer;
import com.demo.spring.entity.Tour;

/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Controller
 *
 */
@Controller
public class TourController {

	@Autowired
	private RestTemplate rt;

	/***
	 * 
	 * @param map
	 * @return registration page
	 */
	@GetMapping(value = "/customer/registration")
	public String customerRegistration(ModelMap map) {

		Customer customer = new Customer();
		map.addAttribute("customer", customer);
		return "registration";
	}

	/***
	 * 
	 * @param customer
	 * @method Customer Registered
	 */
	@PostMapping(value = "/customer/registered")
	public ModelAndView registerCustomer(@ModelAttribute("customer") Customer customer) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<String> response = rt.postForEntity("http://localhost:9192/customer/add", customer,
				String.class);
		mv.addObject(response);
		if (response.getBody().contains("Successfully")) {
			String result = "Registered with " + customer.getUsername() + " ! Please Login to continue";
			mv.addObject("success", result);
			mv.setViewName("registration");
		} else {
			String result = "Username Already Present";
			mv.addObject("success", result);
			mv.setViewName("registration");
		}

		return mv;
	}

	/***
	 * 
	 * @param map
	 * @return login page
	 */
	@GetMapping(value = "/login")
	public String login(ModelMap map) {
		Admin admin = new Admin();
		map.addAttribute("admin", admin);
		return "login";
	}

	/***
	 * 
	 * @param admin
	 * @return login access
	 */
	@PostMapping(value = "/login")
	public ModelAndView loginCheck(@ModelAttribute("admin") Admin admin) {

		ModelAndView mv = new ModelAndView();
		ResponseEntity<String> resp1 = rt.postForEntity("http://localhost:9191/admin/login/", admin, String.class);

		if (resp1.getBody().equals("Admin")) {
			mv.addObject("admin", admin.getUsername());
			mv.setViewName("adminDashboard");
		} else {
			ResponseEntity<String> resp2 = rt.getForEntity(
					"http://localhost:9192/customer/" + admin.getUsername() + "/" + admin.getPassword(), String.class);
			if (resp2.getBody().equals("Customer")) {
				ResponseEntity<Customer> resp3 = rt
						.getForEntity("http://localhost:9192/customer/get/" + admin.getUsername(), Customer.class);
				mv.addObject("customerId", resp3.getBody().getCustomerId());
				mv.addObject("username", admin.getUsername());
				mv.setViewName("customerDashboard");
			} else {
				mv.addObject("access", "Login Failed");
				mv.setViewName("login");

			}
		}

		return mv;

	}

	
	/****
	 * 
	 * @param map
	 * @return tour-list page
	 */
	@GetMapping(value = "/admin/tour")
	public ModelAndView getTourList(ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Tour>> response = rt.exchange("http://localhost:9191/tour/list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Tour>>() {
				});
		mv.setViewName("tourList");
		mv.addObject("list", response.getBody());
		return mv;
	}

	/****
	 * 
	 * @param map
	 * @return addTour page
	 */
	@GetMapping(value = "/tour/add")
	public String getAddTourPage(ModelMap map) {
		Tour tour = new Tour();
		map.addAttribute("specs", tour);
		return "addTour";
	}

	/***
	 * 
	 * @param tour
	 * @method add tour
	 */
	@PostMapping(value = "/tour/added")
	public ModelAndView addSpeciality(@ModelAttribute("tour") Tour tour) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.postForEntity("http://localhost:9191/tour/add", tour, String.class);

		mv.addObject(response);
		String message = "Specuality added Scuessfully! ";
		mv.addObject("message", message);
		mv.setViewName("tourList");
		return mv;

	}

	/****
	 * 
	 * @param map
	 * @return list-customer
	 */
	@GetMapping(value = "/customer")
	public ModelAndView customerList(ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Customer>> response = rt.exchange("http://localhost:9192/customer/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Customer>>() {
				});
		mv.setViewName("customerList");
		mv.addObject("list", response.getBody());
		return mv;
	}

	/****
	 * 
	 * @param map
	 * @return list-bookings
	 */
	@GetMapping(value = "/tour/booked")
	public ModelAndView bookedTours(ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Booking>> response = rt.exchange("http://localhost:9191/booking/list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Booking>>() {
				});
		mv.setViewName("bookedList");
		mv.addObject("list", response.getBody());
		return mv;
	}

	/***
	 * 
	 * @param customerId
	 * @param map
	 * @return list-bookings by customerId
	 */
	@GetMapping(value = "/customer/booked")
	public ModelAndView bookedTours(@RequestParam("customerId") int customerId, ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Booking>> response = rt.exchange("http://localhost:9191/booking/list/" + customerId,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
				});
		mv.setViewName("customerBookedList");
		mv.addObject("list", response.getBody());
		return mv;
	}

	/****
	 * 
	 * @param customerId
	 * @param map
	 * @return customerTour List
	 */
	@GetMapping(value = "/customer/tour")
	public ModelAndView customerTour(@RequestParam("customerId") int customerId, ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Tour>> response = rt.exchange("http://localhost:9191/tour/list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Tour>>() {
				});
		mv.addObject("customerId", customerId);
		mv.setViewName("customerTourList");
		mv.addObject("list", response.getBody());
		return mv;
	}

	/***
	 * 
	 * @param customerId
	 * @param tourId
	 * @param map
	 * @return tourBooking page
	 */
	@GetMapping(value = "/customer/tour/book")
	public ModelAndView customerTour(@RequestParam("customerId") int customerId, @RequestParam("tourId") int tourId,
			ModelMap map) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("customerId", customerId);
		mv.addObject("tourId", tourId);
		mv.setViewName("bookTour");
		return mv;
	}

	/***
	 * 
	 * @param booking
	 * @param tourId
	 * @method Add Booking
	 */
	@PostMapping(value = "/customer/tour/booked")
	public ModelAndView addBooking(@ModelAttribute("booking") Booking booking, @RequestParam("tourId") int tourId) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<String> response = rt.postForEntity("http://localhost:9191/booking/add/" + tourId, booking,
				String.class);

		mv.addObject(response);
		String message = "Booked Successfully. Make Payment! ";
		mv.addObject("message", message);
		mv.setViewName("bookedList");
		return mv;
	}

	/***
	 * 
	 * @param bookingId
	 * @method make payment
	 */
	@GetMapping(value = "/customer/booking/payment")
	public ModelAndView makeTourPayment(@RequestParam("bookingId") int bookingId) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<String> response = rt.getForEntity("http://localhost:9191/booking/update/" + bookingId,
				String.class);

		mv.addObject(response);
		String message = "Payment Done Successfully!";
		mv.addObject("message", message);
		mv.setViewName("success");
		return mv;
	}

}
