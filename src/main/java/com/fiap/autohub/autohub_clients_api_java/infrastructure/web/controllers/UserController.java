package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.controllers; // Ajuste o pacote

import com.fiap.autohub.autohub_clients_api_java.domain.commands.CompleteProfileCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.commands.UserUpdateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;
import com.fiap.autohub.autohub_clients_api_java.domain.ports.in.UserServicePort;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos.CompleteProfileRequestDto;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos.UpdateUserRequestDto;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos.UserResponseDto;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.web.mappers.UserDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Endpoints para gerenciamento de perfis de usuário")
public class UserController {

    private final UserServicePort userService;
    private final UserDtoMapper mapper;

    public UserController(UserServicePort userService, UserDtoMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/me")
    @Operation(summary = "Cria o perfil completo do usuário logado", description = "Cria o registro completo (Nome, CPF, Endereço, etc.) no banco de dados da aplicação pela primeira vez.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil do usuário criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Perfil do usuário já existe para este ID", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDto> createProfile(
            @Valid @RequestBody CompleteProfileRequestDto request,
            @Parameter(hidden = true) @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");

        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        CompleteProfileCommand createCommand = mapper.toCreateCommand(request);
        User createdUser = userService.createProfile(userId, email, createCommand);
        UserResponseDto responseDto = mapper.toResponseDto(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/me")
    @Operation(summary = "Atualiza dados do usuário logado", description = "Permite ao usuário autenticado atualizar/complementar seus dados cadastrais (CPF, CNH, Endereço, etc.).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: dados inválidos)", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualizar", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDto> updateCurrentUser(
            @Valid @RequestBody UpdateUserRequestDto request,
            @Parameter(hidden = true) Principal principal
    ) {
        String userId = principal.getName();
        UserUpdateCommand command = mapper.toUpdateCommand(request);
        User updatedUser = userService.updateUser(userId, command);
        UserResponseDto responseDto = mapper.toResponseDto(updatedUser);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/me")
    @Operation(summary = "Obtém dados do usuário logado", description = "Retorna os dados cadastrais completos do usuário autenticado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do usuário encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado no banco de dados", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDto> getCurrentUser(
            @Parameter(hidden = true) Principal principal
    ) {
        String userId = principal.getName();
        Optional<User> userOpt = userService.findUserById(userId); // Recebe Optional<User>
        // Usa map e orElseGet para tratar o Optional
        return userOpt.map(user -> ResponseEntity.ok(mapper.toResponseDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém dados de um usuário por ID", description = "Retorna os dados de um usuário específico. Requer permissão adequada (ex: admin).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do usuário encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Proibido (sem permissão para acessar este usuário)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDto> getUserById(
            @Parameter(description = "ID (sub Cognito) do usuário a ser buscado") @PathVariable String id
    ) {
        // TODO: Implementar lógica de autorização aqui
        Optional<User> userOpt = userService.findUserById(id); // Recebe Optional<User>
        return userOpt.map(user -> ResponseEntity.ok(mapper.toResponseDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/me")
    @Operation(summary = "Deleta o usuário logado", description = "Remove o registro do usuário autenticado do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para deletar", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteCurrentUser(
            @Parameter(hidden = true) Principal principal
    ) {
        String userId = principal.getName();
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário por ID", description = "Remove o registro de um usuário específico. Requer permissão adequada (ex: admin).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Proibido (sem permissão para deletar este usuário)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para deletar", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteUserById(
            @Parameter(description = "ID (sub Cognito) do usuário a ser deletado") @PathVariable String id
    ) {
        // TODO: Implementar lógica de autorização aqui
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}