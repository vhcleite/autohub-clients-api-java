package com.fiap.autohub.autohub_clients_api_java.domain.ports.in; // Ajuste o pacote se necessário

import com.fiap.autohub.autohub_clients_api_java.domain.commands.UserCreateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.commands.UserUpdateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;

import java.util.Optional;

public interface UserServicePort {

    User createInitialUser(String userId, UserCreateCommand userCreateCommand);

    User updateUser(String userId, UserUpdateCommand command);

    Optional<User> findUserById(String id); // Usa Optional para indicar que pode não encontrar

    void deleteUserById(String id); // Retorno void em Java

}