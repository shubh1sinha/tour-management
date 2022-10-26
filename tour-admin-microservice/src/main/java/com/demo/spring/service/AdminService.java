package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Admin;
import com.demo.spring.entity.Booking;
import com.demo.spring.entity.Tour;
import com.demo.spring.repo.AdminRepository;
import com.demo.spring.repo.BookingRepository;
import com.demo.spring.repo.TourRepository;

/***
 * 
 * @author shubh-sinha
 * @project tour-booking
 * @class AdminServiceDAO implementation
 *
 */
@Service
public class AdminService implements AdminServiceDAO {
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private TourRepository tourRepo;
	
	@Autowired
	private BookingRepository bookingRepo;

	@Override
	/***
	 * @Method give admin access
	 * @param username
	 * @param password
	 * @return true if username matches
	 * @return false if passwrod and username not matches
	 */
	public boolean accessAdmin(String username, String password) {
		Optional<Admin> admin = adminRepo.findById(username);
		if(admin.isPresent()) {
			if(admin.get().getPassword().equals(password)) {
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
	 * @Method add tour
	 * @param Tour
	 * @return String
	 */
	public String addTour(Tour tour) {
		tourRepo.save(tour);
		return "Added sucessfully";
	}

	@Override
	/***
	 * @Method Add Booking
	 * @param booking
	 * @param tourId
	 * @return Success Msg
	 */
	public String addBooking(Booking booking, int tourId) {
		Optional<Tour> tour = tourRepo.findById(tourId);
		if(tour.isEmpty()) {
			return "Tour with id Not found";
		}
		else {
			booking.setPrice(tour.get().getPrice() * booking.getPassengers());
			booking.setTour(tour.get());
			bookingRepo.save(booking);
			return "Booking Added Successfully!";
		}

		
	}

	@Override
	/***
	 * @Method Apprive Booking
	 * @param bookingId
	 * @return msg
	 */
	public String updateBookingStatus(int bookingId) {
		
		Optional<Booking> booking = bookingRepo.findById(bookingId);
		if(booking.isEmpty()) {
			return "No booking Present";
		}
		else {
			booking.get().setStatus(true);
			bookingRepo.save(booking.get());
			return "Status Updated";
		}

	}

	@Override
	/***
	 * @Method Listing tours
	 * @return list
	 */
	public List<Tour> listAllTours() {
		return tourRepo.findAll();
	}

	@Override
	/**
	 * @Method List booking by customerId
	 * @return booking list
	 */
	public List<Booking> listAllBooking(int customerId) {
		return bookingRepo.findAllByCustomerId(customerId);
	}

	@Override
	/***
	 * @Method List All bookings
	 * @return list
	 */
	public List<Booking> listBookings() {
		// TODO Auto-generated method stub
		return bookingRepo.findAll();
	}

	
}
