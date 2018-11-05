package com.eazydineapp.order.request;

import java.io.Serializable;
import java.util.List;

import com.eazydineapp.order.model.OrderLineItem;

public class CreateOrderLineItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;
	private List<OrderLineItem> orderLineItems;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}

	public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}

}
