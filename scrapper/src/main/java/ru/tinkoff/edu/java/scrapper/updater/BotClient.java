package ru.tinkoff.edu.java.scrapper.updater;

import java.time.Duration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;

@Service
@ConditionalOnProperty(prefix = "app",
                       name = "use-queue",
                       havingValue = "false")
public class BotClient implements Updater {
    private static final String BASE_URL = "http://localhost:8081/";
    private static final int SEC = 1000;
    private final WebClient webClient;

    public BotClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public BotClient(final String url) {
        webClient = WebClient.create(url);
    }

    public void updateLink(final LinkUpdateRequest request) {
        webClient.post()
            .uri("updates")
            .bodyValue(request)
            .retrieve().bodyToMono(Void.class)
            .timeout(Duration.ofMillis(SEC))
            .block();
    }

}
