package com.demo.spring.entity;

/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Tour Entity
 *
 */
import java.util.HashSet;
import java.util.Set;

public class Tour {

	private int tourId;

	private String tourName;

	private String description;

	private int duration;

	private String startDate;

	private double price;

	private Set<Booking> booking = new HashSet<>();

	public Tour() {
	}

	public Tour(int tourId, String tourName, String description, int duration, String startDate, int price) {
		this.tourId = tourId;
		this.tourName = tourName;
		this.description = description;
		this.duration = duration;
		this.startDate = startDate;
		this.price = price;
	}

	public int getTourId() {
		return tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
