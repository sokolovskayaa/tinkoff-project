import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
public class EnvironmentTest extends IntegrationEnvironment{
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/scrapper";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    @Test
    public void loadContext() throws SQLException {
        Assertions.assertTrue(postgres.isRunning());
        Connection connection = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        DatabaseMetaData data = connection.getMetaData();
        Assertions.assertTrue(data.getTables(null, null, "chat", null).next());
        Assertions.assertTrue(data.getTables(null, null, "chat_link", null).next());
        Assertions.assertTrue(data.getTables(null, null, "link", null).next());
    }
}
