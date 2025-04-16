package com.fiap.autohub.autohub_clients_api_java.domain.services;

import com.fiap.autohub.autohub_clients_api_java.domain.commands.AddressUpdateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.commands.UserCreateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.commands.UserUpdateCommand;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.Address;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;
import com.fiap.autohub.autohub_clients_api_java.domain.exceptions.UserAlreadyExistsException;
import com.fiap.autohub.autohub_clients_api_java.domain.exceptions.UserNotFoundException;
import com.fiap.autohub.autohub_clients_api_java.domain.ports.in.UserServicePort;
import com.fiap.autohub.autohub_clients_api_java.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepository;

    public UserServiceImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createInitialUser(String userId, UserCreateCommand userCreateCommand) {
        Optional<User> existingUserOpt = userRepository.findById(userId);
        if (existingUserOpt.isPresent()) {
            throw new UserAlreadyExistsException(userId);
        }

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        User userToSave = new User(
                userId,
                userCreateCommand.firstName(),
                userCreateCommand.lastName(),
                userCreateCommand.email(),
                null,
                null,
                null,
                now,
                now
        );
        return userRepository.save(userToSave);
    }

    @Override
    public User updateUser(String userId, UserUpdateCommand command) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        User updatedUser = mapUpdateUserCommand(existingUser, command);
        return userRepository.save(updatedUser);
    }

    private User mapUpdateUserCommand(User existingUser, UserUpdateCommand command) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        return new User(
                existingUser.id(),
                command.firstName() != null ? command.firstName() : existingUser.firstName(),
                command.lastName() != null ? command.lastName() : existingUser.lastName(),
                existingUser.email(),
                command.cpf() != null ? command.cpf() : existingUser.cpf(),
                command.cnh() != null ? command.cnh() : existingUser.cnh(),
                mapUpdateAddressCommand(command.address(), existingUser.address()),
                existingUser.createdAt(),
                now
        );
    }

    private Address mapUpdateAddressCommand(AddressUpdateCommand commandAddress, Address existingAddress) {
        if (commandAddress == null) {
            return existingAddress;
        }
        return new Address(
                commandAddress.street(),
                commandAddress.number(),
                commandAddress.complement(),
                commandAddress.neighborhood(),
                commandAddress.city(),
                commandAddress.state(),
                commandAddress.zipCode()
        );
    }


    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}