package com.demo.spring.entity;
/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Booking Entity
 *
 */
public class Booking {

	private int bookingId;

	private int customerId;

	private int passengers;

	private double price;

	private boolean status;

	private Tour tour;

	public Booking() {
	}

	public Booking(int bookingId, int customerId, int passengers, double price, boolean status) {
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.passengers = passengers;
		this.price = price;
		this.status = status;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

}
