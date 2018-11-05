package com.eazydineapp.order.dao;

import java.util.List;

import com.eazydineapp.order.model.Order;

/**
 * Order Repository to save/retrieve from DynamoDB
 * @author Anushri Srinath Aithal
 *
 */
public interface OrderDao {

	/**
	 * Create Order
	 * @param order
	 */
	void createOrUpdate(Order order);

	/**
	 * Fetch order by user id
	 * @param value
	 * @param sortciteria
	 * @return
	 */
	List<Order> getOrdersByUserId(String value, Boolean sortciteria);

	/**
	 * Fetch order by restaurant id
	 * @param userId
	 * @param value
	 * @param sortciteria
	 * @return
	 */
	List<Order> getOrdersByRestaurantId(String userId, String value, Boolean sortciteria);

	/**
	 * Fetch order by order status
	 * @param userId
	 * @param value
	 * @param sortciteria
	 * @return
	 */
	List<Order> getOrdersByOrderStatus(String userId, String value, Boolean sortciteria);

	/**
	 * Fetch order by its id
	 * @param orderId
	 * @return
	 */
	Order getOrderByOrderId(String orderId);

}
