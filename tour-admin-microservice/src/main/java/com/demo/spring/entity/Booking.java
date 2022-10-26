package com.demo.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Booking Entity
 *
 */
@Entity
@Table(name = "BOOKING")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bookingId")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKINGID")
	private int bookingId;

	@Column(name = "CUSTOMERID")
	private int customerId;

	@Column(name = "PASSENGERS")
	private int passengers;

	@Column(name = "PRICE")
	private double price;

	@Column(name = "STATUS")
	private boolean status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TOURID", referencedColumnName = "TOURID")
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
