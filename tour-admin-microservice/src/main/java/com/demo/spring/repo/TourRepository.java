package com.demo.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.entity.Tour;
/***
 * 
 * @author shubh-sinha
 * @peoject tour-management
 * @Class Tour-Repository
 *
 */

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer>{

}
