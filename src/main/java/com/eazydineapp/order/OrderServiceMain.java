package com.eazydineapp.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan
@Configuration
@EnableConfigurationProperties
public class OrderServiceMain {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceMain.class, args);
	}
}
