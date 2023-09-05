package ru.tinkoff.edu.java.scrapper.updater;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;

@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
@Service
public class ScrapperQueueProducer implements Updater {
    private final RabbitTemplate rabbitTemplate;

    public void updateLink(LinkUpdateRequest update) {
        rabbitTemplate.convertAndSend("scrapper", update);
    }
}
