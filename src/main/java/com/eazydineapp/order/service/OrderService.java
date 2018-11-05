package com.eazydineapp.order.service;

import java.util.List;

import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.response.OrderResponse;

/**
 * Service Layer for all Order APIs
 * @author Anushri Srinath Aithal
 *
 */
public interface OrderService {

	/**
	 * Create Order
	 * @param createOrderRequest
	 * @return
	 */
	OrderResponse createOrder(OrderRequest createOrderRequest);

	/**
	 * Get Order based on various filters and sort criteria
	 * @param userId
	 * @param name
	 * @param value
	 * @param sort
	 * @return
	 */
	List<OrderResponse> getOrders(String userId, String name, String value, String sort);

}
