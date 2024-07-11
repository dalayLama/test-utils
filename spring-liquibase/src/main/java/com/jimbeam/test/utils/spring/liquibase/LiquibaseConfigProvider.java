package com.jimbeam.test.utils.spring.liquibase;

import org.springframework.lang.Nullable;

public interface LiquibaseConfigProvider {

    String getChangeLogPath();

    @Nullable String getLiquibaseSchema();

    @Nullable String getDefaultSchema();

}
