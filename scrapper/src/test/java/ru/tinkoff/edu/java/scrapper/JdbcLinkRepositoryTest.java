package ru.tinkoff.edu.java.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Chat;
import ru.tinkoff.edu.java.scrapper.dto.repository.jdbc.ChatLink;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;

//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
@SpringBootTest
//@ContextConfiguration(classes = IntegrationEnvironment.IntegrationEnvironmentConfig.class)
@RunWith(SpringRunner.class)
@Slf4j
public class JdbcLinkRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JdbcLinkRepository linkRepository;
    @Autowired
    private JpaChatRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.url}")
    private String url;

    @Rollback
    @Test
    public void addTest() {
        Chat chat = new Chat();
        chat.setId(1234L);
        repository.save(chat);
        Assertions.assertEquals(1, repository.findAll().size());
//        Assertions.assertNotNull(linkRepository);
//        linkRepository.addLink("fghjkl;';lkjhgfdfgthjkl");
//        var links = jdbcTemplate.query("select * from link", new BeanPropertyRowMapper<>(ChatLink.class));
//        Assertions.assertEquals(1, links.size());
    }

    @Rollback
    public void removeTest() {
    }

}