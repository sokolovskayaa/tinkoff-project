package ru.tinkoff.edu.java.scrapper.webclient;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.updater.BotClient;
import ru.tinkoff.edu.java.scrapper.updater.Updater;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BotClientTest extends IntegrationEnvironment {
    @Autowired
    private Updater updater;

    @Test
    public void BotClientBeanTest() {
        Assertions.assertTrue(updater instanceof BotClient);
    }

}
