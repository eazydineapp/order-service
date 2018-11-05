package com.eazydineapp.order.request;

import java.io.Serializable;
import java.util.List;

public class UpdateOrderStatusRequest implements Serializable {

	private static final long serialVersionUID = -8061898840578930405L;

	private String orderId;
	private List<OrderLineItemRequest> orderLineItemStatus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderLineItemRequest> getOrderLineItemStatus() {
		return orderLineItemStatus;
	}

	public void setOrderLineItemStatus(List<OrderLineItemRequest> orderLineItemStatus) {
		this.orderLineItemStatus = orderLineItemStatus;
	}

}
