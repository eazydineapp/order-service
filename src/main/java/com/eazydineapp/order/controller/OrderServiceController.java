package com.eazydineapp.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.response.OrderResponse;
import com.eazydineapp.order.service.OrderService;

/**
 * Rest Controller for Order Microservice
 * @author Anushri Srinath Aithal
 *
 */
@RestController
@RequestMapping("order")
public class OrderServiceController {

	@Autowired
	OrderService orderService;

	/**
	 * Create Order API
	 * @param createOrderRequest
	 * @return
	 */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderResponse createOrder(@RequestBody OrderRequest createOrderRequest) {
		return orderService.createOrder(createOrderRequest);
	}

	/**
	 * Get Order API using various filters
	 * @param userId
	 * @param name
	 * @param value
	 * @param sort
	 * @return
	 */
	@GetMapping(value = "/")
	public List<OrderResponse> getOrders(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "value", required = false) String value,
			@RequestParam(name = "sort", required = false) String sort) {
		return orderService.getOrders(userId, name, value, sort);
	}

}
