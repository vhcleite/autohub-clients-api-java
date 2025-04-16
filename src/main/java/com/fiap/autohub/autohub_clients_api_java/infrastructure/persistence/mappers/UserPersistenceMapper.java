package com.fiap.autohub.autohub_clients_api_java.infrastructure.persistence.mappers;

import com.fiap.autohub.autohub_clients_api_java.domain.entities.Address;
import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.persistence.entities.AddressPersistenceEntity;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.persistence.entities.UserPersistenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = "spring")
public abstract class UserPersistenceMapper {

    private static final Logger logger = LoggerFactory.getLogger(UserPersistenceMapper.class);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    public abstract UserPersistenceEntity toPersistenceEntity(User user);

    public abstract AddressPersistenceEntity toPersistenceEntity(Address address);

    @Mappings({
            @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "stringToOffsetDateTime"),
            @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "stringToOffsetDateTime")
    })
    public abstract User toDomainEntity(UserPersistenceEntity entity);

    public abstract Address toDomainEntity(AddressPersistenceEntity entity);


    @Named("stringToOffsetDateTime")
    public OffsetDateTime stringToOffsetDateTime(String dateTimeString) {
        if (dateTimeString == null) {
            return null;
        }
        try {
            return OffsetDateTime.parse(dateTimeString);
        } catch (DateTimeParseException e) {
            logger.error("Failed to parse timestamp string: {}", dateTimeString, e);
            return OffsetDateTime.MIN;
        }
    }
}