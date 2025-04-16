package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa os dados de resposta de um usuário")
public class UserResponseDto {

    @Schema(description = "ID único do usuário (Cognito Sub)", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String id;

    @Schema(description = "Primeiro nome do usuário", example = "João")
    private String firstName;

    @Schema(description = "Sobrenome do usuário", example = "Silva")
    private String lastName;

    @Schema(description = "Endereço de e-mail do usuário", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "CPF do usuário (formatado ou não)", example = "111.222.333-44", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String cpf;

    @Schema(description = "Número da CNH do usuário", example = "98765432101", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String cnh;

    @Schema(description = "Endereço do usuário", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private AddressDto address;

    @Schema(description = "Data e hora de criação do registro (ISO 8601 UTC)", example = "2025-04-06T00:25:10.123Z")
    private String createdAt;

    @Schema(description = "Data e hora da última atualização (ISO 8601 UTC)", example = "2025-04-06T00:30:00.456Z")
    private String updatedAt;

    public UserResponseDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}