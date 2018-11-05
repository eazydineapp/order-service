package com.eazydineapp.order.response;

import java.io.Serializable;
import java.util.List;

import com.eazydineapp.order.model.OrderLineItem;
import com.eazydineapp.order.model.OrderStatus;
import com.eazydineapp.order.model.OrderType;

public class OrderResponse  implements Serializable {

	private static final long serialVersionUID = 5312528063870975426L;
	private String orderId;
	private String restuarantId;
	private String restaurantName;
	private String userId;
	private String groupId;
	private OrderStatus orderStatus;
	private OrderType orderType;
	private List<OrderLineItem> orderLineItems;
	private Double amount;
	private String createdTime;
	private String modifiedTime;
	private Boolean markForDelete;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRestuarantId() {
		return restuarantId;
	}

	public void setRestuarantId(String restuarantId) {
		this.restuarantId = restuarantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}

	public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Boolean getMarkForDelete() {
		return markForDelete;
	}

	public void setMarkForDelete(Boolean markForDelete) {
		this.markForDelete = markForDelete;
	}
}
