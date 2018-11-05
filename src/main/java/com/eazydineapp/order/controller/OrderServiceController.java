package com.eazydineapp.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazydineapp.order.request.CreateOrderLineItem;
import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.request.UpdateOrderQuantityRequest;
import com.eazydineapp.order.request.UpdateOrderStatusRequest;
import com.eazydineapp.order.response.OrderResponse;
import com.eazydineapp.order.service.OrderService;

/**
 * Rest Controller for Order Microservice
 * 
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
	 * 
	 * @param createOrderRequest
	 * @return
	 */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest createOrderRequest) {
		OrderResponse order = orderService.createOrder(createOrderRequest);
		return new ResponseEntity<OrderResponse>(order, HttpStatus.OK);
	}

	/**
	 * Get Order API using various filters
	 * 
	 * @param userId
	 * @param name
	 * @param value
	 * @param sort
	 * @return
	 */
	@GetMapping(value = "/")
	public ResponseEntity<?> getOrders(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "value", required = false) String value,
			@RequestParam(name = "sort", required = false) String sort) {
		List<OrderResponse> orders = orderService.getOrders(userId, name, value, sort);
		return new ResponseEntity<List<OrderResponse>>(orders, HttpStatus.OK);
	}
	
	/**
	 * API to update order line status and derive order status
	 * @param UpdateOrderStatusRequest
	 * @return
	 */
	@PutMapping(value="/status", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateOrderLineStatus(@RequestBody UpdateOrderStatusRequest orderRequest) {
		OrderResponse order = orderService.updateOrderLineStatus(orderRequest);
		return new ResponseEntity<OrderResponse>(order, HttpStatus.OK);
	}
	
	/**
	 * API to update order line item quantity
	 * @param UpdateOrderQuantityRequest
	 * @return
	 */
	@PutMapping(value="/quantity", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateOrderLineQuantity(@RequestBody UpdateOrderQuantityRequest orderRequest) {
		OrderResponse order = orderService.updateOrderLineQuantity(orderRequest);
		return new ResponseEntity<OrderResponse>(order, HttpStatus.OK);
	}
	
	/**
	 * API to add new order line item
	 * @param UpdateOrderQuantityRequest
	 * @return
	 */
	@PostMapping(value="/line/item", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createNewOrderLineItem(@RequestBody CreateOrderLineItem orderRequest) {
		OrderResponse order = orderService.createNewOrderLineItem(orderRequest);
		return new ResponseEntity<OrderResponse>(order, HttpStatus.OK);
	}

}
