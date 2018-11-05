package com.eazydineapp.order.request;

import java.io.Serializable;

import com.eazydineapp.order.model.OrderStatus;

public class OrderLineItemRequest implements Serializable {

	private static final long serialVersionUID = 7805628336310872105L;

	private String itemId;
	private OrderStatus lineItemStatus;
	private Integer quantity;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public OrderStatus getLineItemStatus() {
		return lineItemStatus;
	}

	public void setLineItemStatus(OrderStatus lineItemStatus) {
		this.lineItemStatus = lineItemStatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
