//package ru.tinkoff.edu.java.scrapper.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
//import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
//import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkUpdateRepository;
//import ru.tinkoff.edu.java.scrapper.service.ChatService;
//import ru.tinkoff.edu.java.scrapper.service.LinkService;
//import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcChatService;
//import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
//import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkUpdater;
//import ru.tinkoff.edu.java.scrapper.webclient.BotClient;
//import ru.tinkoff.edu.java.scrapper.webclient.GitHubClient;
//import ru.tinkoff.edu.java.scrapper.webclient.StackOverflowClient;
//
//@Configuration
//@RequiredArgsConstructor
//@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
//public class JdbcAccessConfiguration {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Bean
//    public JdbcChatRepository jdbcChatRepository() {
//        return new JdbcChatRepository(jdbcTemplate);
//    }
//
//    @Bean
//    public JdbcLinkRepository jdbcLinkRepository() {
//        return new JdbcLinkRepository(jdbcTemplate);
//    }
//
//    @Bean
//    public JdbcLinkUpdateRepository jdbcLinkUpdateRepository() {
//        return new JdbcLinkUpdateRepository(jdbcTemplate);
//    }
//
//    @Bean
//    public JdbcLinkUpdater jdbcLinkUpdater(JdbcLinkUpdateRepository repository, BotClient botClient,
//                                           GitHubClient gitHubClient, StackOverflowClient stackOverflowClient) {
//        return new JdbcLinkUpdater(repository, gitHubClient, stackOverflowClient, botClient);
//    }
//
//    @Bean
//    public ChatService chatService(JdbcChatRepository repository) {
//        return new JdbcChatService(repository);
//    }
//
//    @Bean
//    public LinkService linkService(JdbcLinkRepository repository) {
//        return new JdbcLinkService(repository);
//    }
//
//}
