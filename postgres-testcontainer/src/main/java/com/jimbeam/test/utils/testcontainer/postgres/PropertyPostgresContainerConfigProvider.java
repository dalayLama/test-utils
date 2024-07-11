package com.jimbeam.test.utils.testcontainer.postgres;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class PropertyPostgresContainerConfigProvider implements PostgresContainerConfigProvider {

    @Value("${container.postgres.image:postgres:16.3}")
    private String image;

    @Value("${container.postgres.db-name:test}")
    private String databaseName;

    @Value("${container.postgres.username:postgres}")
    private String username;

    @Value("${container.postgres.password:postgres}")
    private String password;

    @Value("${container.postgres.init-script-path:#{null}}")
    private String initScriptPath;

}
