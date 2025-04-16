package com.fiap.autohub.autohub_clients_api_java.domain.entities;

import java.time.OffsetDateTime;

public record User(
        String id,
        String firstName,
        String lastName,
        String email,
        String cpf,
        String cnh,
        Address address,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {

}