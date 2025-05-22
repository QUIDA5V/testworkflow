package com.vip.repository;

import com.vip.model.PicCountry;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PicCountryRepository {

    public void save(PicCountry picCountry){
        DynamoDbClient client = DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Change region as needed
                .build();

        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();

        DynamoDbTable<PicCountry> productTable = enhancedClient.table("PicCountry", TableSchema.fromBean(PicCountry.class));

        productTable.putItem(picCountry);
        System.out.println("Item saved successfully!");

    }

    public void update(){
        String tableName = "PicCountry";
        String partitionKey = "userId";
        String sortKey = "placeId";

        String customerIdValue = "CUST123";
        String orderIdValue = "ORD456";

        String attributeToUpdate = "status";
        String newValue = "0";

        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Set your region
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        // Define the composite key
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(partitionKey, AttributeValue.builder().s(customerIdValue).build());
        key.put(sortKey, AttributeValue.builder().s(orderIdValue).build());

        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#attr", attributeToUpdate);

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":val", AttributeValue.builder().s(newValue).build());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .updateExpression("SET #attr = :val")
                .expressionAttributeNames(expressionAttributeNames)
                .expressionAttributeValues(expressionAttributeValues)
                .returnValues("UPDATED_NEW")
                .build();

        UpdateItemResponse response = ddb.updateItem(request);
        System.out.println("Updated item: " + response.attributes());
    }
}
