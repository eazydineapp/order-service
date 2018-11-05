package com.eazydineapp.order.model;

public enum OrderQueryParameter {
	
	UserId("UserId"), RestuarantId("RestuarantId"), OrderStatus("OrderStatus");
	
	String value;
	
	OrderQueryParameter(String value) {
		this.value = value;
	}
}
