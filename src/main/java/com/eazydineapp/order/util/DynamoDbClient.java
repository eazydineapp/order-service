package com.eazydineapp.order.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * DynamoDB utility class
 * @author Anushri Srinath Aithal
 *
 */
@Service
public class DynamoDbClient {

	private AmazonDynamoDB dynamoDB;
	
	@Value("${aws.region}")
	private String region;
	
	@PostConstruct
	private void init() {
		dynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance()).withRegion(region).build();
	}

	public AmazonDynamoDB getDynamoDB() {
		if (dynamoDB == null) {
			init();
		}
		return dynamoDB;
	}

	public void setDynamoDB(AmazonDynamoDB dynamoDB) {
		this.dynamoDB = dynamoDB;
	}

	public DynamoDBMapper getDynamoDBMapper() {
		AmazonDynamoDB dynamoDB = getDynamoDB();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
		return mapper;
	}
}
