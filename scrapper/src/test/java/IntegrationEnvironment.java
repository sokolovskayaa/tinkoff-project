import liquibase.Liquibase;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.DirectoryResourceAccessor;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest
public abstract class IntegrationEnvironment {
    @ClassRule
    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer("postgres:14")
            .withDatabaseName("scrapper")
            .withUsername("postgres")
            .withPassword("password");

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        postgres.start();
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
    }

//    @BeforeAll
//    public static void startContainer() {
//        testContainer.start();
////        Liquibase liquibase = new liquibase.Liquibase("scrapper/migrations/master.xml", new ClassLoaderResourceAccessor("scrapper/migrations/master.xml"), testContainer);
//    }
}
