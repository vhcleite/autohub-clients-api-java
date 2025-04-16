package com.fiap.autohub.autohub_clients_api_java.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("User not found with id: " + id);
    }
}