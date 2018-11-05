package com.eazydineapp.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.response.OrderResponse;
import com.eazydineapp.order.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderServiceController {

	@Autowired
	OrderService orderService;
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderResponse createOrder(@RequestBody OrderRequest createOrderRequest) {
		return orderService.createOrder(createOrderRequest);
	}
	
}
