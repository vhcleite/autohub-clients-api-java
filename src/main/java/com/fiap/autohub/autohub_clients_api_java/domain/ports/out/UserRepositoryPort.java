package com.fiap.autohub.autohub_clients_api_java.domain.ports.out;

import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;

import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(String id);

    void deleteById(String id);

}