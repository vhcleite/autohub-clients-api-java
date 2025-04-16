package com.fiap.autohub.autohub_clients_api_java.infrastructure.persistence.entities;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbFlatten;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
public class UserPersistenceEntity {

    private String id; // Cognito Sub
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String cnh;
    private AddressPersistenceEntity address;
    private String createdAt;
    private String updatedAt;

    public UserPersistenceEntity() {
    }

    @DynamoDbPartitionKey // Define 'id' como chave de partição
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = {"email-index"})
    // Define 'email' como chave de partição do GSI chamado 'email-index'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    @DynamoDbFlatten
    public AddressPersistenceEntity getAddress() {
        return address;
    }

    public void setAddress(AddressPersistenceEntity address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}