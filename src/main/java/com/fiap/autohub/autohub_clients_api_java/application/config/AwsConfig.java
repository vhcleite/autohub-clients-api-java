package com.fiap.autohub.autohub_clients_api_java.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class AwsConfig {

    private final String awsRegion;

    public AwsConfig(@Value("${aws.region}") String awsRegion) {
        this.awsRegion = awsRegion;
    }

    @Bean
    @Profile("!local")
    public DynamoDbEnhancedClient dynamoDbEnhancedClientProd() {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(Region.of(this.awsRegion))
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    @Profile("local")
    public DynamoDbEnhancedClient dynamoDbEnhancedClientLocal(
            @Value("${aws.dynamodb.endpoint}") String dynamoDbEndpoint,
            @Value("${aws.credentials.accessKey}") String accessKey,
            @Value("${aws.credentials.secretKey}") String secretKey
    ) {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(Region.of(this.awsRegion))
                .endpointOverride(URI.create(dynamoDbEndpoint))
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))
                )
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }
}