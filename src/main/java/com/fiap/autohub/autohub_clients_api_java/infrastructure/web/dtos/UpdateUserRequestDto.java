package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos; // Ajuste o pacote

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para atualização/complemento do perfil do usuário autenticado.")
public class UpdateUserRequestDto {

    @Schema(description = "Primeiro nome atualizado do usuário (opcional para envio, pode ser nulo se não for alterar)", example = "João Carlos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String firstName; // JSON: first_name

    @Schema(description = "Sobrenome atualizado do usuário (opcional para envio, pode ser nulo se não for alterar)", example = "Silva Santos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String lastName; // JSON: last_name

    @NotBlank(message = "CPF cannot be blank")
    @Schema(description = "CPF do usuário (obrigatório nesta atualização)", example = "111.222.333-44", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;

    @NotBlank(message = "CNH cannot be blank")
    @Schema(description = "Número da CNH do usuário (obrigatório nesta atualização)", example = "98765432101", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cnh;

    @Valid
    @Schema(description = "Endereço completo atualizado do usuário (opcional para envio, mas campos internos podem ser obrigatórios se o objeto for enviado)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private AddressDto address;

    public UpdateUserRequestDto() {
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