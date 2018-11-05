package com.eazydineapp.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazydineapp.order.dao.OrderDao;
import com.eazydineapp.order.model.Order;
import com.eazydineapp.order.model.OrderLineItem;
import com.eazydineapp.order.model.OrderStatus;
import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.response.OrderResponse;
import com.eazydineapp.order.service.OrderService;
import com.google.gson.Gson;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	private Gson gson= new Gson();

	@Override
	public OrderResponse createOrder(OrderRequest createOrderRequest) {
		String json = gson.toJson(createOrderRequest);
		Order order = gson.fromJson(json, Order.class);
		order.setOrderId(UUID.randomUUID().toString());
		order.setOrderStatus(OrderStatus.CREATED);
		
		String currentTime = String.valueOf(System.currentTimeMillis());
		
		order.setCreatedTime(currentTime);
		order.setModifiedTime(currentTime);
		
		List<OrderLineItem> orderLineItems = new ArrayList<>();
		for(OrderLineItem lineItem : createOrderRequest.getOrderLineItems()) {
			lineItem.setCreatedTime(currentTime);
			lineItem.setModifiedTime(currentTime);
			lineItem.setLineItemStatus(OrderStatus.CREATED);
			lineItem.setItemId(UUID.randomUUID().toString());
			orderLineItems.add(lineItem);
		}
		
		order.setOrderLineItems(orderLineItems);
		orderDao.createOrUpdate(order);
		
		String orderJson = gson.toJson(order);
		OrderResponse response = gson.fromJson(orderJson, OrderResponse.class);
		return response;
	}
}
