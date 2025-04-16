package com.fiap.autohub.autohub_clients_api_java.domain.commands;


public record CompleteProfileCommand(
        String firstName,
        String lastName,
        String cpf,
        String cnh,
        AddressUpdateCommand address
) {
}