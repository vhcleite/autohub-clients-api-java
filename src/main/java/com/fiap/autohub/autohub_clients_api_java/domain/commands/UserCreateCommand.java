package com.fiap.autohub.autohub_clients_api_java.domain.commands; // Ajuste o pacote se necessário

public record UserCreateCommand(
        String firstName,
        String lastName,
        String email
) {
    
}