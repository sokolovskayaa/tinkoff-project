package ru.tinkoff.edu.java.scrapper.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;

import java.time.Duration;

@Component
public class BotClient {
    private final static String BASE_URL = "http://localhost:8081/";
    private final WebClient webClient;

    public BotClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public BotClient(String url) {
        webClient = WebClient.create(url);
    }

    public void updateLink(LinkUpdateRequest request) {
        webClient.post()
                .uri("updates")
                .bodyValue(request)
                .retrieve().bodyToMono(Void.class)
                .timeout(Duration.ofMillis(10000))
                .block();
    }

}
