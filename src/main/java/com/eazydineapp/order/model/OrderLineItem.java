package com.eazydineapp.order.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

/**
 * Order Line Item Entity
 * 
 * @author Anushri Srinath Aithal
 *
 */
@DynamoDBDocument
public class OrderLineItem {

	private String lineItemId;
	private String itemId;
	private String itemName;
	private OrderStatus lineItemStatus;
	private String createdTime;
	private String modifiedTime;
	private Integer quantity;

	public String getLineItemId() {
		return lineItemId;
	}

	public void setLineItemId(String lineItemId) {
		this.lineItemId = lineItemId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@DynamoDBTypeConvertedEnum
	public OrderStatus getLineItemStatus() {
		return lineItemStatus;
	}

	public void setLineItemStatus(OrderStatus lineItemStatus) {
		this.lineItemStatus = lineItemStatus;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
