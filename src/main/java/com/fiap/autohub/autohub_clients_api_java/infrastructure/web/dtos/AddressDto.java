package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Representa os dados de endereço do usuário")
public class AddressDto {

    @NotBlank(message = "Street cannot be blank")
    @Schema(description = "Nome da rua/logradouro", example = "Avenida Principal", requiredMode = Schema.RequiredMode.REQUIRED)
    private String street;

    @NotBlank(message = "Number cannot be blank")
    @Schema(description = "Número do imóvel", example = "123 B", requiredMode = Schema.RequiredMode.REQUIRED)
    private String number;

    @Schema(description = "Complemento do endereço (bloco, apto, etc.)", example = "Apto 101", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String complement;

    @NotBlank(message = "Neighborhood cannot be blank")
    @Schema(description = "Bairro", example = "Centro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String neighborhood;

    @NotBlank(message = "City cannot be blank")
    @Schema(description = "Cidade", example = "Diadema", requiredMode = Schema.RequiredMode.REQUIRED)
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 2, max = 2, message = "State must be 2 characters")
    @Schema(description = "Sigla do estado (UF)", example = "SP", requiredMode = Schema.RequiredMode.REQUIRED)
    private String state;

    @NotBlank(message = "Zip code cannot be blank")
    @Schema(description = "Código de Endereçamento Postal (CEP)", example = "09910-170", requiredMode = Schema.RequiredMode.REQUIRED)
    private String zipCode;

    public AddressDto() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}