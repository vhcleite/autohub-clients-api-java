package com.fiap.autohub.autohub_clients_api_java.domain.commands; // Ajuste o pacote se necessário

public record AddressUpdateCommand(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode
) {
    
}