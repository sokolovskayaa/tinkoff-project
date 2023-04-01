import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.GitHubRepositoryResponse;

public class ScrapperClient {

    private final static String BASE_URL = "http://localhost:8080/";
    private final WebClient webClient;

    private ScrapperClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public Mono<GitHubRepositoryResponse> addChat(long id) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("tg-chat/{id}")
                .build(id)).retrieve()
                .bodyToMono(GitHubRepositoryResponse.class);
    }

//    public Mono<>
}
