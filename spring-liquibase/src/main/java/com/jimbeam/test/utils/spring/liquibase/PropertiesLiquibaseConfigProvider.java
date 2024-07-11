package com.jimbeam.test.utils.spring.liquibase;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class PropertiesLiquibaseConfigProvider implements LiquibaseConfigProvider {

    @Value("${liquibase.change-log-path}")
    private String changeLogPath;

    @Value("${liquibase.schema:#{null}}")
    private String liquibaseSchema;

    @Value("${liquibase.default-schema:#{null}}")
    private String defaultSchema;

}
