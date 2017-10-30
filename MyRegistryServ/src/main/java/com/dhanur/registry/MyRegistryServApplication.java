package com.dhanur.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 227761
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class MyRegistryServApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRegistryServApplication.class, args);
	}
}
