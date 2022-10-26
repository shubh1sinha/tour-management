package com.demo.spring.entity;

/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Customer Entity
 *
 */
public class Customer {

	private int customerId;

	private String name;

	private String username;

	private String password;

	private double phoneNumber;

	private int age;

	public Customer() {
	}

	public Customer(int customerId, String name, String username, String password, double phoneNumber, int age) {
		this.customerId = customerId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.age = age;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
