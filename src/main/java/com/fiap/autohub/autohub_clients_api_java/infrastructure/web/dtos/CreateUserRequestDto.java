package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados básicos para criação inicial do perfil do usuário após autenticação no Cognito.")
public class CreateUserRequestDto {

    @NotBlank(message = "First name cannot be blank")
    @Schema(description = "Primeiro nome do usuário", example = "Maria", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Schema(description = "Sobrenome do usuário", example = "Souza", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Schema(description = "Endereço de e-mail principal do usuário (deve coincidir com o usado no Cognito)", example = "maria.souza@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    public CreateUserRequestDto() {
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
}