//package ru.tinkoff.edu.java.scrapper;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//@SpringBootTest
//public class EnvironmentTest extends IntegrationEnvironment {
//
//    @Test
//    public void loadContext() throws SQLException {
//        Assertions.assertTrue(IntegrationEnvironment.postgres.isRunning());
//        Connection connection = DriverManager.getConnection(IntegrationEnvironment.postgres.getJdbcUrl(), IntegrationEnvironment.postgres.getUsername(), IntegrationEnvironment.postgres.getPassword());
//        DatabaseMetaData data = connection.getMetaData();
//        Assertions.assertTrue(data.getTables(null, null, "chat", null).next());
//        Assertions.assertTrue(data.getTables(null, null, "chat_link", null).next());
//        Assertions.assertTrue(data.getTables(null, null, "link", null).next());
//    }
//}
