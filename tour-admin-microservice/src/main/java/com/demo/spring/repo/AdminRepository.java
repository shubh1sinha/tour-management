package com.demo.spring.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.entity.Admin;

/***
 * 
 * @author shubh-sinha
 * @project tour-booking
 * @class admin repository
 *
 */

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {


}
