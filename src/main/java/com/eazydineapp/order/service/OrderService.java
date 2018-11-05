package com.eazydineapp.order.service;

import com.eazydineapp.order.request.OrderRequest;
import com.eazydineapp.order.response.OrderResponse;

public interface OrderService {

	OrderResponse createOrder(OrderRequest createOrderRequest);

}
