package com.fiap.autohub.autohub_clients_api_java.domain.ports.in; // Ajuste o pacote se necessário

import com.fiap.autohub.autohub_clients_api_java.domain.commands.CompleteProfileCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.commands.UserUpdateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;

import java.util.Optional;

public interface UserServicePort {

    User createProfile(String userId, String email, CompleteProfileCommand command);

    User updateUser(String userId, UserUpdateCommand command);

    Optional<User> findUserById(String id);

    void deleteUserById(String id);

}