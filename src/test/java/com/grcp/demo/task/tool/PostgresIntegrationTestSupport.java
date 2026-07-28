package com.grcp.demo.task.tool;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

abstract class PostgresIntegrationTestSupport {

    static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER = new PostgreSQLContainer<>("postgres:17-alpine")
            .withDatabaseName("task_tool")
            .withUsername("task_tool")
            .withPassword("task_tool");

    static {
        POSTGRESQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void configureDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
    }
}
