package com.fiap.autohub.autohub_clients_api_java.domain.entities;

public record Address(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode
) {
}