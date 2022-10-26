package com.demo.spring.service;

import java.util.List;

import com.demo.spring.entity.Booking;
import com.demo.spring.entity.Tour;

/***
 * 
 * @author shubh-sinha
 * @project tour-managemnt
 * @interface AdminServiceDAO
 *
 */
public interface AdminServiceDAO {
	
	public boolean accessAdmin(String username, String password);
	
	public String addTour(Tour tour);
	
	public String addBooking(Booking booking, int tourId);
	
	public String updateBookingStatus(int bookingId);
	
	public List<Tour> listAllTours();
	
	public List<Booking> listAllBooking(int customerId);
	
	public List<Booking> listBookings();

}
