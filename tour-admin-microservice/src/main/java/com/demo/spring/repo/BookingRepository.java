package com.demo.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.entity.Booking;

/***
 * 
 * @author shubh-sinha
 * @project tour-booking
 * @class Booking Repository
 *
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findAllByCustomerId(int customerId);
	
}
