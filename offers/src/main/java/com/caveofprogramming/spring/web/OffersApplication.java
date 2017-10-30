package com.caveofprogramming.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 227761 This is main class for offer microservice
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class OffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[] { OffersApplication.class }, args);
	}

}
