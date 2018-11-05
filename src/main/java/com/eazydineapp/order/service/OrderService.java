package com.eazydineapp.order.service;

import java.util.List;

import com.eazydineapp.order.request.CreateOrderLineItem;
import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.request.UpdateOrderQuantityRequest;
import com.eazydineapp.order.request.UpdateOrderStatusRequest;
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

	/**
	 * Update order line status and derive order status
	 * @param orderRequest
	 * @return
	 */
	OrderResponse updateOrderLineStatus(UpdateOrderStatusRequest orderRequest);

	/**
	 * Update order line quantity
	 * @param orderRequest
	 * @return
	 */
	OrderResponse updateOrderLineQuantity(UpdateOrderQuantityRequest orderRequest);

	/**
	 * Add new order line item
	 * @param orderRequest
	 * @return
	 */
	OrderResponse createNewOrderLineItem(CreateOrderLineItem orderRequest);

}
