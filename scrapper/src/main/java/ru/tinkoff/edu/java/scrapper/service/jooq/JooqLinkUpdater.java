package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.edu.java.linkParser.link.GitHubParsedLink;
import ru.tinkoff.edu.java.linkParser.link.ParsedLink;
import ru.tinkoff.edu.java.linkParser.link.StackOverflowParsedLink;
import ru.tinkoff.edu.java.linkParser.parser.LinkParser;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.jdbc.Link;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkUpdateRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdater;
import ru.tinkoff.edu.java.scrapper.webclient.BotClient;
import ru.tinkoff.edu.java.scrapper.webclient.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclient.StackOverflowClient;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JooqLinkUpdater implements LinkUpdater {

    private final JooqLinkUpdateRepository linkUpdateRepository;
    private final LinkParser linkParser = new LinkParser();
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final BotClient botClient;


    @Override
    public void update() {
        List<Link> links = linkUpdateRepository.getLinks();
        for (var link : links) {
            isLinkUpdated(link);
        }
    }

    public void isLinkUpdated(Link link) {
        ParsedLink parsedLink = linkParser.parseLink(link.getUrl());
        if (parsedLink instanceof GitHubParsedLink) {
            checkGithubLink(parsedLink, link);
        }
        if (parsedLink instanceof StackOverflowParsedLink) {
            checkStackoverflowLink(parsedLink, link);
        }
    }

    public void checkGithubLink(ParsedLink parsedLink, Link link) {
        if (parsedLink instanceof GitHubParsedLink) {
            log.info("link {} is a github repo", link.getUrl());
            var commits =
                    gitHubClient.getCommits(((GitHubParsedLink) parsedLink).id(),
                            ((GitHubParsedLink) parsedLink).repo(),
                            link.getUpdatedAt());
            if (!commits.isEmpty()) {
                log.info("new commit in repo {} ", link.getUrl());
                linkUpdateRepository.updateLink(link);
                notifyChats(link, String.format("New commit in repo %s", link.getUrl()));
            }
            var repo =
                    gitHubClient.getRepo(((GitHubParsedLink) parsedLink).id(),
                            ((GitHubParsedLink) parsedLink).repo());
            if (repo.pushedAt().isAfter(link.getUpdatedAt())) {
                log.info("link {} was updated", link.getUrl());
                linkUpdateRepository.updateLink(link);
                notifyChats(link, String.format("Repo %s has been updated", link.getUrl()));
            }
        }
    }

    public void checkStackoverflowLink(ParsedLink parsedLink, Link link) {
        log.info("link {} is a Stackoverflow question", link.getUrl());
        var answers = stackOverflowClient
                .getAnswers(((StackOverflowParsedLink) parsedLink).id(), link.getUpdatedAt());
        if (!answers.answers().isEmpty()) {
            log.info("Question {} has new answer", link.getUrl());
            linkUpdateRepository.updateLink(link);
            notifyChats(link, String.format("Question %s has new answer", ((StackOverflowParsedLink) parsedLink).id()));
        }
        var questions = stackOverflowClient
                .getQuestion(((StackOverflowParsedLink) parsedLink).id());
        if (questions.questions().get(0).lastActivityDate().isAfter(link.getUpdatedAt())) {
            log.info("link {} was updated", link.getUrl());
            linkUpdateRepository.updateLink(link);
            notifyChats(link, String.format("Question %s has been updated", ((StackOverflowParsedLink) parsedLink).id()));
        }

    }

    public void notifyChats(Link link, String message) {
        log.info(message, link.getUrl());
        List<Long> chats = linkUpdateRepository.getChats(link.getId()).stream().map(ChatLink::getChatId).toList();
        for (var chat : chats) {
            log.info("chat {}", chat);
        }
        LinkUpdateRequest request = new LinkUpdateRequest(link.getId(), link.getUrl(), message, chats);
        botClient.updateLink(request);
    }
}
