package com.eazydineapp.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.eazydineapp.order.dao.OrderDao;
import com.eazydineapp.order.model.Order;
import com.eazydineapp.order.util.DynamoDbClient;

/**
 * Repository implementation class
 * @author Anushri Srinath Aithal
 *
 */
@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	DynamoDbClient dynamodbClient;

	@Override
	public void createOrUpdate(Order order) {
		DynamoDBMapper mapper = dynamodbClient.getDynamoDBMapper();
		mapper.save(order);
	}

	@Override
	public List<Order> getOrdersByUserId(String value, Boolean sortciteria) {
		DynamoDBMapper mapper = dynamodbClient.getDynamoDBMapper();

		Map<String, String> expressionAttributesNames = new HashMap<>();
		expressionAttributesNames.put("#userId", "userId");

		Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
		expressionAttributeValues.put(":userIdValue", new AttributeValue().withS(value));

		DynamoDBQueryExpression<Order> dynamoDBQueryExpression = new DynamoDBQueryExpression<Order>()
				.withIndexName("userId-createdTime-index").withKeyConditionExpression("#userId = :userIdValue")
				.withExpressionAttributeNames(expressionAttributesNames)
				.withExpressionAttributeValues(expressionAttributeValues).withScanIndexForward(sortciteria)
				.withConsistentRead(false);

		List<Order> orders = mapper.query(Order.class, dynamoDBQueryExpression);
		return orders;
	}

	@Override
	public List<Order> getOrdersByRestaurantId(String userId, String value, Boolean sortciteria) {
		DynamoDBMapper mapper = dynamodbClient.getDynamoDBMapper();

		Map<String, String> expressionAttributesNames = new HashMap<>();
		expressionAttributesNames.put("#userId", "userId");
		expressionAttributesNames.put("#restuarantId", "restuarantId");

		Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
		expressionAttributeValues.put(":userIdValue", new AttributeValue().withS(userId));
		expressionAttributeValues.put(":restuarantIdValue", new AttributeValue().withS(value));

		DynamoDBQueryExpression<Order> dynamoDBQueryExpression = new DynamoDBQueryExpression<Order>()
				.withIndexName("userId-createdTime-index")	//GSI name
				.withKeyConditionExpression("#userId = :userIdValue")	//hash key condition
				.withFilterExpression("#restuarantId = :restuarantIdValue")	//filter condition
				.withExpressionAttributeNames(expressionAttributesNames)
				.withExpressionAttributeValues(expressionAttributeValues).withScanIndexForward(sortciteria)
				.withConsistentRead(false);

		List<Order> orders = mapper.query(Order.class, dynamoDBQueryExpression);
		return orders;
	}

	@Override
	public List<Order> getOrdersByOrderStatus(String userId, String value, Boolean sortciteria) {
		DynamoDBMapper mapper = dynamodbClient.getDynamoDBMapper();

		Map<String, String> expressionAttributesNames = new HashMap<>();
		expressionAttributesNames.put("#userId", "userId");
		expressionAttributesNames.put("#orderStatus", "orderStatus");

		Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
		expressionAttributeValues.put(":userIdValue", new AttributeValue().withS(userId));
		expressionAttributeValues.put(":orderStatusValue", new AttributeValue().withS(value));

		DynamoDBQueryExpression<Order> dynamoDBQueryExpression = new DynamoDBQueryExpression<Order>()
				.withIndexName("userId-createdTime-index").withKeyConditionExpression("#userId = :userIdValue")
				.withFilterExpression("#orderStatus = :orderStatusValue")
				.withExpressionAttributeNames(expressionAttributesNames)
				.withExpressionAttributeValues(expressionAttributeValues).withScanIndexForward(sortciteria)
				.withConsistentRead(false);

		List<Order> orders = mapper.query(Order.class, dynamoDBQueryExpression);
		return orders;
	}

}
