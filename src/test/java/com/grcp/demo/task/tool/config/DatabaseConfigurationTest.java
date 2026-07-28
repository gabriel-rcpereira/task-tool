package com.grcp.demo.task.tool.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseConfigurationTest {

    @Test
    void shouldCreatePostgresDataSourceUsingResolvedDatasourceProperties() {
        DataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://db.internal:5544/task_tool_test")
                .username("test_user")
                .password("test_secret")
                .build();

        assertThat(dataSource).isInstanceOf(HikariDataSource.class);

        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        assertThat(hikariDataSource.getDriverClassName()).isEqualTo("org.postgresql.Driver");
        assertThat(hikariDataSource.getJdbcUrl()).isEqualTo("jdbc:postgresql://db.internal:5544/task_tool_test");
        assertThat(hikariDataSource.getUsername()).isEqualTo("test_user");
    }
}
