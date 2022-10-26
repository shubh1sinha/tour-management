package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Admin Entity
 *
 */
@Entity
@Table(name = "ADMIN")
public class Admin {

	@Id
	@Column(name = "USERNAME")
	String username;

	@Column(name = "PASSWORD")
	String password;

	public Admin() {
	}

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
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

}
