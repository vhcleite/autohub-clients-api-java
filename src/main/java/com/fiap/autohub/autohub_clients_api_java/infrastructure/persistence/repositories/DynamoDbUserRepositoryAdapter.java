package com.fiap.autohub.autohub_clients_api_java.infrastructure.persistence.repositories;

import com.fiap.autohub.autohub_clients_api_java.domain.entities.User;
import com.fiap.autohub.autohub_clients_api_java.domain.ports.out.UserRepositoryPort;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.persistence.entities.UserPersistenceEntity;
import com.fiap.autohub.autohub_clients_api_java.infrastructure.persistence.mappers.UserPersistenceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

@Repository
public class DynamoDbUserRepositoryAdapter implements UserRepositoryPort {

    private final UserPersistenceMapper mapper;

    private final DynamoDbTable<UserPersistenceEntity> userTable;

    public DynamoDbUserRepositoryAdapter(
            DynamoDbEnhancedClient enhancedClient,
            UserPersistenceMapper mapper,
            @Value("${dynamodb.table-name}") String tableName) {
        this.mapper = mapper;
        this.userTable = enhancedClient.table(
                tableName,
                TableSchema.fromBean(UserPersistenceEntity.class)
        );
    }

    @Override
    public User save(User user) {
        UserPersistenceEntity persistenceEntity = mapper.toPersistenceEntity(user);

        if (user.createdAt() != null) {
            persistenceEntity.setCreatedAt(user.createdAt().toString());
        }
        if (user.updatedAt() != null) {
            persistenceEntity.setUpdatedAt(user.updatedAt().toString());
        }

        userTable.putItem(persistenceEntity);

        return mapper.toDomainEntity(persistenceEntity);
    }

    @Override
    public Optional<User> findById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        UserPersistenceEntity persistenceEntity = userTable.getItem(key);
        return Optional.ofNullable(persistenceEntity)
                .map(mapper::toDomainEntity);
    }

    @Override
    public void deleteById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        userTable.deleteItem(key);
    }
}