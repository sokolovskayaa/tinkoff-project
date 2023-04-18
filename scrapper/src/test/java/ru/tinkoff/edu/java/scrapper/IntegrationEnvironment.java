package ru.tinkoff.edu.java.scrapper;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class IntegrationEnvironment {

    @TestConfiguration
    public static class IntegrationEnvironmentConfig {

        @Bean("dataSourse")
        public DataSource dataSource() {
            return DataSourceBuilder.create()
                    .url(postgres.getJdbcUrl())
                    .username(postgres.getUsername())
                    .password(postgres.getPassword())
                    .build();
        }
    }

    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer("postgres:14")
            .withDatabaseName("scrapper")
            .withUsername("postgres")
            .withPassword("password");

    static {
        try {
            postgres.start();
            Connection connection = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new liquibase.Liquibase("master.xml", new DirectoryResourceAccessor(new File("migrations").toPath().toAbsolutePath()), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception ignored) {

        }
    }
}
