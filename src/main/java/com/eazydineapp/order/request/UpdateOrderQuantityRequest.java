package com.eazydineapp.order.request;

import java.io.Serializable;
import java.util.List;

public class UpdateOrderQuantityRequest implements Serializable {

	private static final long serialVersionUID = 8533282017379982429L;

	private String orderId;
	private List<OrderLineItemRequest> lineItemQty;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderLineItemRequest> getLineItemQty() {
		return lineItemQty;
	}

	public void setLineItemQty(List<OrderLineItemRequest> lineItemQty) {
		this.lineItemQty = lineItemQty;
	}

}
