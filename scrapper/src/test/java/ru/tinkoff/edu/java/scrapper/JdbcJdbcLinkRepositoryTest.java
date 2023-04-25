package ru.tinkoff.edu.java.scrapper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import ru.tinkoff.edu.java.scrapper.dto.repository.ChatLink;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;

@SpringBootTest(classes = IntegrationEnvironment.IntegrationEnvironmentConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JdbcJdbcLinkRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JdbcLinkRepository linkRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Rollback
    @Test
    public void addTest() {
        linkRepository.add(1, "fghjkl");
        var links = jdbcTemplate.query("select * from chat_link", new BeanPropertyRowMapper<>(ChatLink.class));
        Assert.assertEquals(links.size(), 1);
    }

    @Rollback
    public void removeTest() {
    }

}