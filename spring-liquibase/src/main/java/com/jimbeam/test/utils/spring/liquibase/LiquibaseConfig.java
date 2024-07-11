package com.jimbeam.test.utils.spring.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration(proxyBeanMethods = false)
public class LiquibaseConfig {

    public LiquibaseConfig() {
    }

    @Bean
    SpringLiquibase springLiquibase(DataSource dataSource,
                                    LiquibaseConfigProvider configProvider) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(configProvider.getChangeLogPath());
        liquibase.setLiquibaseSchema(configProvider.getLiquibaseSchema());
        liquibase.setDefaultSchema(configProvider.getDefaultSchema());
        return liquibase;
    }

    @ConditionalOnMissingBean
    @Bean
    LiquibaseConfigProvider liquibaseConfigProvider() {
        return new PropertiesLiquibaseConfigProvider();
    }

}
