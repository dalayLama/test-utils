package com.jimbeam.test.utils.testcontainer.postgres;

import org.springframework.lang.Nullable;

public interface PostgresContainerConfigProvider {

    String getImage();

    String getDatabaseName();

    String getUsername();

    String getPassword();

    @Nullable String getInitScriptPath();

}
