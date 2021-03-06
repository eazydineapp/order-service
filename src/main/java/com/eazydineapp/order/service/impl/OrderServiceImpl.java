package com.eazydineapp.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.eazydineapp.order.dao.OrderDao;
import com.eazydineapp.order.model.Order;
import com.eazydineapp.order.model.OrderLineItem;
import com.eazydineapp.order.model.OrderQueryParameter;
import com.eazydineapp.order.model.OrderSortCriteria;
import com.eazydineapp.order.model.OrderStatus;
import com.eazydineapp.order.request.CreateOrderLineItem;
import com.eazydineapp.order.request.OrderLineItemRequest;
import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.request.UpdateOrderQuantityRequest;
import com.eazydineapp.order.request.UpdateOrderStatusRequest;
import com.eazydineapp.order.response.OrderResponse;
import com.eazydineapp.order.service.OrderService;
import com.google.gson.Gson;

/**
 * Order Service Implementation class
 * 
 * @author Anushri Srinath Aithal
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	private Gson gson = new Gson();

	@Override
	public OrderResponse createOrder(OrderRequest createOrderRequest) {
		String json = gson.toJson(createOrderRequest);
		Order order = gson.fromJson(json, Order.class);
		order.setOrderId(UUID.randomUUID().toString()); // generate order id
		order.setOrderStatus(OrderStatus.CREATED); // always create new order in created state

		String currentTime = String.valueOf(System.currentTimeMillis()); // generate created time

		order.setCreatedTime(currentTime);
		order.setModifiedTime(currentTime);

		List<OrderLineItem> orderLineItems = new ArrayList<>();
		for (OrderLineItem lineItem : createOrderRequest.getOrderLineItems()) {
			lineItem.setCreatedTime(currentTime);
			lineItem.setModifiedTime(currentTime);
			lineItem.setLineItemStatus(OrderStatus.CREATED); // order line always in created state
			lineItem.setItemId(UUID.randomUUID().toString());
			orderLineItems.add(lineItem);
		}

		order.setOrderLineItems(orderLineItems);
		orderDao.createOrUpdate(order);

		String orderJson = gson.toJson(order);
		OrderResponse response = gson.fromJson(orderJson, OrderResponse.class);
		return response;
	}

	@Override
	public List<OrderResponse> getOrders(String userId, String name, String value, String sort) {

		Boolean sortciteria = (sort == null || OrderSortCriteria.valueOf(sort) == OrderSortCriteria.DESC) ? false
				: true;

		OrderQueryParameter filterCriteria = OrderQueryParameter.UserId;
		if (!StringUtils.isEmpty(name)) {
			filterCriteria = OrderQueryParameter.valueOf(name);
		}

		List<Order> orders = null;
		switch (filterCriteria) {

		case UserId:
			orders = orderDao.getOrdersByUserId(userId, sortciteria); // user id filter sort on create time
			break;

		case RestuarantId:
			orders = orderDao.getOrdersByRestaurantId(userId, value, sortciteria); // user id and restaurant filter sort
																					// on create time
			break;

		case OrderStatus:
			orders = orderDao.getOrdersByOrderStatus(userId, value, sortciteria); // user id and order status filter
																					// sort on create time
			break;
		}

		List<OrderResponse> response = new ArrayList<>();
		if (!CollectionUtils.isEmpty(orders)) {
			for (Order order : orders) {
				String orderJson = gson.toJson(order);
				OrderResponse orderResponse = gson.fromJson(orderJson, OrderResponse.class);
				response.add(orderResponse);
			}
		}
		return response;
	}

	@Override
	public OrderResponse updateOrderLineStatus(UpdateOrderStatusRequest orderRequest) {
		Order order = orderDao.getOrderByOrderId(orderRequest.getOrderId());

		if (null == order) {
			// TODO: throw exception
		}

		if (OrderStatus.SERVED == order.getOrderStatus() || OrderStatus.READY == order.getOrderStatus()
				|| OrderStatus.CANCELLED == order.getOrderStatus()) {
			// TODO Invalid state exception
		}

		Map<String, OrderLineItemRequest> orderLineItemReq = orderRequest.getOrderLineItemStatus().stream()
				.collect(Collectors.toMap(OrderLineItemRequest::getItemId, oli -> oli));

		String currentTime = String.valueOf(System.currentTimeMillis()); // generate modified time

		List<OrderLineItem> orderLineItems = new ArrayList<>();
		for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
			OrderLineItemRequest newOrderLineItem = orderLineItemReq.get(orderLineItem.getItemId());
			if (null != newOrderLineItem) {
				orderLineItem.setLineItemStatus(newOrderLineItem.getLineItemStatus());
				orderLineItem.setModifiedTime(currentTime);
			}
			orderLineItems.add(orderLineItem);
		}

		OrderStatus orderStatus = deriveOrderStatusFromLineItem(orderLineItems);

		order.setOrderStatus(orderStatus);
		order.setModifiedTime(currentTime);

		orderDao.createOrUpdate(order);

		String orderJson = gson.toJson(order);
		OrderResponse response = gson.fromJson(orderJson, OrderResponse.class);
		return response;
	}

	@Override
	public OrderResponse updateOrderLineQuantity(UpdateOrderQuantityRequest orderRequest) {
		Order order = orderDao.getOrderByOrderId(orderRequest.getOrderId());

		if (null == order) {
			// TODO: throw exception
		}

		if (OrderStatus.SERVED == order.getOrderStatus() || OrderStatus.READY == order.getOrderStatus()
				|| OrderStatus.CANCELLED == order.getOrderStatus()) {
			// TODO Invalid state exception
		}

		Map<String, OrderLineItemRequest> orderLineItemReq = orderRequest.getLineItemQty().stream()
				.collect(Collectors.toMap(OrderLineItemRequest::getItemId, oli -> oli));

		String currentTime = String.valueOf(System.currentTimeMillis()); // generate modified time

		List<OrderLineItem> orderLineItems = new ArrayList<>();
		for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
			OrderLineItemRequest newOrderLineItem = orderLineItemReq.get(orderLineItem.getItemId());
			if (null != newOrderLineItem) {
				if (OrderStatus.CREATED != orderLineItem.getLineItemStatus()) {
					// TODO throw exception
				}
				orderLineItem.setQuantity(newOrderLineItem.getQuantity());
				orderLineItem.setModifiedTime(currentTime);
			}
			orderLineItems.add(orderLineItem);
		}

		order.setModifiedTime(currentTime);

		orderDao.createOrUpdate(order);

		String orderJson = gson.toJson(order);
		OrderResponse response = gson.fromJson(orderJson, OrderResponse.class);
		return response;
	}

	@Override
	public OrderResponse createNewOrderLineItem(CreateOrderLineItem orderRequest) {
		Order order = orderDao.getOrderByOrderId(orderRequest.getOrderId());

		if (null == order) {
			// TODO: throw exception
		}
		if (OrderStatus.CANCELLED == order.getOrderStatus()) {
			// TODO Invalid state exception
		}
		order.setOrderStatus(OrderStatus.CREATED);
		String currentTime = String.valueOf(System.currentTimeMillis()); // generate modified time

		order.setModifiedTime(currentTime);

		List<OrderLineItem> orderLineItems = order.getOrderLineItems();
		for (OrderLineItem lineItem : orderRequest.getOrderLineItems()) {
			lineItem.setCreatedTime(currentTime);
			lineItem.setModifiedTime(currentTime);
			lineItem.setLineItemStatus(OrderStatus.CREATED); // order line always in created state
			lineItem.setItemId(UUID.randomUUID().toString());
			orderLineItems.add(lineItem);
		}

		orderDao.createOrUpdate(order);

		String orderJson = gson.toJson(order);
		OrderResponse response = gson.fromJson(orderJson, OrderResponse.class);
		return response;
	}

	private OrderStatus deriveOrderStatusFromLineItem(List<OrderLineItem> orderLineItems) {
		// If Any in created - created

		// If all cancelled - cancelled

		// If all served / cancelled - served

		// If all start preparing - start prep
		return null;
	}
}
