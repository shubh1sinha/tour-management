package com.demo.spring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.entity.Customer;

/***
 * 
 * @author shusinha5
 * @Project tour-booking
 * @Class Customer-Repository
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public boolean existsByUsername(String username);

	public Optional<Customer> findByUsername(String username);

}
