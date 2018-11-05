package com.eazydineapp.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * EazyDineApp Order Microservice
 * @author Anushri Srinath Aithal
 *
 */
@SpringBootApplication
@ComponentScan
@Configuration
@EnableConfigurationProperties
public class OrderServiceMain {
	
	//Spring Boot App
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceMain.class, args);
	}
}
