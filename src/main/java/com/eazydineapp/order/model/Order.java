package com.eazydineapp.order.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

/**
 * Order Entity
 * @author Anushri Srinath Aithal
 *
 */
@DynamoDBTable(tableName = "Order")
public class Order {

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

	@DynamoDBHashKey(attributeName = "orderId")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@DynamoDBAttribute(attributeName = "restuarantId")
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

	@DynamoDBIndexHashKey(attributeName = "userId", globalSecondaryIndexName = "userId-createdTime-index")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@DynamoDBAttribute(attributeName = "groupId")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@DynamoDBTypeConvertedEnum
	@DynamoDBAttribute(attributeName = "orderStatus")
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@DynamoDBTypeConvertedEnum
	@DynamoDBAttribute(attributeName = "orderType")
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@DynamoDBAttribute(attributeName = "orderLineItems")
	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}

	public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}

	@DynamoDBAttribute(attributeName = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@DynamoDBRangeKey(attributeName = "createdTime")
	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	@DynamoDBAttribute(attributeName = "modifiedTime")
	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@DynamoDBAttribute(attributeName = "markForDelete")
	public Boolean getMarkForDelete() {
		return markForDelete;
	}

	public void setMarkForDelete(Boolean markForDelete) {
		this.markForDelete = markForDelete;
	}

}
