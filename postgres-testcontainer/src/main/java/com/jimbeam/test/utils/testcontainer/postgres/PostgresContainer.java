package com.jimbeam.test.utils.testcontainer.postgres;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

import java.nio.file.Path;
import java.util.Optional;

@TestConfiguration(proxyBeanMethods = false)
public class PostgresContainer {

    private static final String POSTGRES_INIT_SCRIPTS_PATH = "/docker-entrypoint-initdb.d/init.sql";

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer(PostgresContainerConfigProvider configProvider){
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>(configProvider.getImage())
                .withDatabaseName(configProvider.getDatabaseName())
                .withUsername(configProvider.getUsername())
                .withPassword(configProvider.getPassword());
        Optional.ofNullable(configProvider.getInitScriptPath())
                .map(Path::of)
                .map(MountableFile::forHostPath)
                .ifPresent(file -> container.withCopyFileToContainer(file, POSTGRES_INIT_SCRIPTS_PATH));
        return container;
    }

    @ConditionalOnMissingBean
    @Bean
    PostgresContainerConfigProvider postgresContainerConfigProvider() {
        return new PropertyPostgresContainerConfigProvider();
    }


}
