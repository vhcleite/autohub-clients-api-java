package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.mappers; // Ajuste o pacote

import com.fiap.autohub.autohub_clients_api_java.domain.commands.AddressUpdateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.commands.CompleteProfileCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.commands.UserUpdateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.Address;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos.AddressDto;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos.CompleteProfileRequestDto;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos.UpdateUserRequestDto;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mappings({
            @Mapping(target = "createdAt", expression = "java(user.createdAt().toString())"),
            @Mapping(target = "updatedAt", expression = "java(user.updatedAt().toString())")
    })
    UserResponseDto toResponseDto(User user);

    AddressDto toDto(Address address);

    UserUpdateCommand toUpdateCommand(UpdateUserRequestDto dto);

    AddressUpdateCommand toUpdateCommand(AddressDto dto);

    CompleteProfileCommand toCreateCommand(CompleteProfileRequestDto dto);
}
