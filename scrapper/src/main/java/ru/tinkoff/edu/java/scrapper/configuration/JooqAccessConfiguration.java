//package ru.tinkoff.edu.java.scrapper.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.jooq.DSLContext;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqChatRepository;
//import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkRepository;
//import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkUpdateRepository;
//import ru.tinkoff.edu.java.scrapper.service.ChatService;
//import ru.tinkoff.edu.java.scrapper.service.LinkService;
//import ru.tinkoff.edu.java.scrapper.service.jooq.JooqChatService;
//import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkService;
//import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkUpdater;
//import ru.tinkoff.edu.java.scrapper.webclient.BotClient;
//import ru.tinkoff.edu.java.scrapper.webclient.GitHubClient;
//import ru.tinkoff.edu.java.scrapper.webclient.StackOverflowClient;
//
//@Configuration
//@RequiredArgsConstructor
//@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
//public class JooqAccessConfiguration {
//    private final DSLContext dslContext;
//
//    @Bean
//    public JooqChatRepository jooqChatRepository() {
//        return new JooqChatRepository(dslContext);
//    }
//
//    @Bean
//    public JooqLinkRepository jooqLinkRepository() {
//        return new JooqLinkRepository(dslContext);
//    }
//
//    @Bean
//    public JooqLinkUpdateRepository jooqLinkUpdateRepository() {
//        return new JooqLinkUpdateRepository(dslContext);
//    }
//
//    @Bean
//    public JooqLinkUpdater jooqLinkUpdater(JooqLinkUpdateRepository repository, BotClient botClient,
//                                           GitHubClient gitHubClient, StackOverflowClient stackOverflowClient) {
//        return new JooqLinkUpdater(repository, gitHubClient, stackOverflowClient, botClient);
//    }
//
//    @Bean
//    public ChatService chatService(JooqChatRepository repository) {
//        return new JooqChatService(repository);
//    }
//
//    @Bean
//    public LinkService linkService(JooqLinkRepository repository) {
//        return new JooqLinkService(repository);
//    }
//
//}
//
