package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.webclient.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclient.StackOverflowClient;

@Configuration
public class ClientConfiguration {

    @Bean(name = "GitHubClient")
    public GitHubClient gitHubClient(){
        return new GitHubClient();
    }

    @Bean(name = "StackOverflowClient")
    public StackOverflowClient stackOverflowClient(){
        return new StackOverflowClient();
    }

}
