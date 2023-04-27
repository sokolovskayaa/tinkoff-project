package ru.tinkoff.edu.java.scrapper.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Chat;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class JpaChatRepositoryTest extends IntegrationEnvironment {


    @Autowired
    private JpaChatRepository repository;

    @Test
    @Rollback
    @Transactional
    public void addChatTest() {
        Chat chat = new Chat();
        chat.setId(1234L);
        repository.save(chat);
        Assertions.assertTrue(repository.existsById(1234L));
    }

    @Test
    @Rollback
    @Transactional
    public void removeTest() {
        Chat chat = new Chat();
        chat.setId(1234L);
        repository.delete(chat);
        Assertions.assertFalse(repository.existsById(1234L));
    }

}