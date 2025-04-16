package com.fiap.autohub.autohub_clients_api_java.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String id) {
        super("User already exists with id: " + id);
    }
}