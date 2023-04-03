package ru.tinkoff.edu.java.bot.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;

public class ScrapperClient {

    private final static String BASE_URL = "http://localhost:8080/";
    private final WebClient webClient;

    private ScrapperClient() {
        webClient = WebClient.create(BASE_URL);
    }

    private ScrapperClient(String baseUrl) {
        webClient = WebClient.create(baseUrl);
    }

    public boolean addChat(long id) {
        webClient.post()
                .uri(uriBuilder -> uriBuilder.path("tg-chat/{id}")
                        .build(id))
                .retrieve();
        return true;
    }

    public boolean deleteChat(long id) {
        webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("tg-chat/{id}")
                        .build(id)).retrieve();
        return true;
    }

    public ListLinksResponse getLinks(Long id) {
        return webClient.get()
                .uri("/links")
                .header("Tg-Chat-Id", id.toString())
                .retrieve().bodyToMono(ListLinksResponse.class).block();
    }

    public boolean addLink(Long id, AddLinkRequest request) {
        webClient.post()
                .uri("/links")
                .header("Tg-Chat-Id", id.toString())
                .bodyValue(request)
                .retrieve();
        return true;
    }

    public boolean deleteLink(Long id, AddLinkRequest request) {
        webClient.method(HttpMethod.DELETE)
                .uri("/links")
                .header("Tg-Chat-Id", id.toString())
                .bodyValue(request)
                .retrieve();
        return true;
    }
}

