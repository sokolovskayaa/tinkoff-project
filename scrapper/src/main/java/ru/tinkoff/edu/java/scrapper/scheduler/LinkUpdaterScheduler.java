package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Chat;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkUpdaterRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdater;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkUpdater;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private final JdbcLinkUpdater linkUpdater;
    private final JpaLinkRepository repository;

    @Scheduled(fixedDelayString = "1000")
    public void update() {
        log.info("start checking for updates...");

//        repository.addLink("dfghjklkjhgghjkl");
//        Link link = repository.findAllByUrl("dfghjklkjhgghjkl").get(0);
        repository.removeLinkById(67);
//        linkUpdater.update();
    }
}
