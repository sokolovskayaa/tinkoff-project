package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.webclient.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclient.StackOverflowClient;

@Configuration
public class ClientConfiguration {

    @Bean(name = "gitHubClientConfig")
    public GitHubClient gitHubClient(){
        return new GitHubClient();
    }

    @Bean(name = "stackOverflowClientConfig")
    public StackOverflowClient stackOverflowClient(){
        return new StackOverflowClient();
    }

}
