package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados completos para criação do perfil do usuário no banco de dados da aplicação.")
public class CompleteProfileRequestDto {

    @NotBlank(message = "First name cannot be blank")
    @Schema(description = "Primeiro nome do usuário", example = "Maria", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Schema(description = "Sobrenome do usuário", example = "Souza", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @NotBlank(message = "CPF cannot be blank")
    @Schema(description = "CPF do usuário", example = "111.222.333-44", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;

    @NotBlank(message = "CNH cannot be blank")
    @Schema(description = "Número da CNH do usuário", example = "98765432101", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cnh;

    @Valid
    @NotNull(message = "Address cannot be null")
    @Schema(description = "Endereço completo do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
    private AddressDto address;

    public CompleteProfileRequestDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}