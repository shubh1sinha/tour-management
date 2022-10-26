package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TourAdminMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourAdminMicroserviceApplication.class, args);

	}

}
