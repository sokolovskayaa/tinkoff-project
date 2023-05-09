package ru.tinkoff.edu.java.bot.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;

import java.time.Duration;

@Slf4j
@Component
public class ScrapperClient {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String LINK_URL = "links";
    private static final String CHAT_URL = "tg-chat/{id}";
    private static final String TG_HEADER = "Tg-Chat-Id";
    private final WebClient webClient;

    public ScrapperClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public ScrapperClient(String baseUrl) {
        webClient = WebClient.create(baseUrl);
    }

    public void addChat(long chatId) {
            webClient.post()
                    .uri(uriBuilder -> uriBuilder.path(CHAT_URL)
                            .build(chatId))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .timeout(Duration.ofMillis(1000))
                    .block();

        log.info("add chat {}", chatId);
    }

    public void deleteChat(long chatId) {
        webClient.delete()
                .uri(uriBuilder -> uriBuilder.path(CHAT_URL)
                        .build(chatId))
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofMillis(10000))
                .block();
        log.info("sent delete chat {} to scrapper", chatId);

    }

    public ListLinksResponse getLinks(Long chatId) {
        log.info("sent get links {} to scrapper", chatId);
        return webClient.get()
                .uri("/links")
                .header(TG_HEADER, chatId.toString())
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .timeout(Duration.ofMillis(10000))
                .block();
    }

    public void addLink(Long chatId, AddLinkRequest request) {
        log.info(String.valueOf(chatId), request);
        webClient.post()
                .uri(LINK_URL)
                .header(TG_HEADER, chatId.toString())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofMillis(10000))
                .block();
        log.info("add link {} to chat {} to scrapper", request.link(), chatId);
    }

    public void deleteLink(Long chatId, RemoveLinkRequest request) {
        webClient.method(HttpMethod.DELETE)
                .uri(LINK_URL)
                .header(TG_HEADER, chatId.toString())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofMillis(10000))
                .block();
        log.info("delete link {} to chat {} to scrapper", request.url(), chatId);

    }
}

