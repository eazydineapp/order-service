package com.eazydineapp.order.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.eazydineapp.order.dao.OrderDao;
import com.eazydineapp.order.model.Order;
import com.eazydineapp.order.util.DynamoDbClient;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	DynamoDbClient dynamodbClient;
	

	@Override
	public void createOrUpdate(Order order) {
		DynamoDBMapper mapper =  dynamodbClient.getDynamoDBMapper();
		mapper.save(order);
	}

}
