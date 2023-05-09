package ru.tinkoff.edu.java.scrapper.configuration.acces;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.updater.Updater;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkUpdaterRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdater;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaChatService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkUpdater;
import ru.tinkoff.edu.java.scrapper.webclient.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclient.StackOverflowClient;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    @Bean
    public ChatService chatService(JpaChatRepository repository) {
        return new JpaChatService(repository);
    }

    @Bean
    public LinkService linkService(JpaLinkRepository repository) {
        return new JpaLinkService(repository);
    }

    @Bean
    public LinkUpdater jpaLinkUpdater(JpaLinkUpdaterRepository repository, Updater updater,
                                      GitHubClient gitHubClient, StackOverflowClient stackOverflowClient) {
        return new JpaLinkUpdater(repository, gitHubClient, stackOverflowClient, updater);
    }
}
