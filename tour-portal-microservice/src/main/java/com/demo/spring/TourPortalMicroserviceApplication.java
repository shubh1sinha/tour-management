package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

/***
 * 
 * @author shubh-sinha
 * @project tour-management
 * @class Runner
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TourPortalMicroserviceApplication {

	RestTemplate rt;

	public static void main(String[] args) {
		SpringApplication.run(TourPortalMicroserviceApplication.class, args);

	}
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
