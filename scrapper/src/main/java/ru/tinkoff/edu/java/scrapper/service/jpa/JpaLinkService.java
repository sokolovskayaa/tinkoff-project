package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkParser.link.ParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;
import ru.tinkoff.edu.java.linkParser.parser.LinkParser;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;
import ru.tinkoff.edu.java.scrapper.exception.InvalidTrackLinkException;
import ru.tinkoff.edu.java.scrapper.exception.InvalidUntrackLinkException;
import ru.tinkoff.edu.java.scrapper.exception.LinkIsAlreadyTrackedException;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Primary

public class JpaLinkService implements LinkService {

    private final JpaLinkRepository linkRepository;
    private final LinkParser linkParser = new LinkParser();

    @Override
    public void add(long chatId, URI url) {
        String linkUrl = url.toString();
        ParsedLink parsedLink = linkParser.parseLink(linkUrl);
        if (parsedLink instanceof UnsupportedParsedLink) {
            log.info("cant track link {}", linkUrl);
            throw new InvalidTrackLinkException();
        }
        if(linkRepository.findAllByUrl(linkUrl).isEmpty()) {
            linkRepository.addLink(linkUrl);
        }
        Link link = linkRepository.findAllByUrl(linkUrl).get(0);
        if(linkRepository.getChatLinksByUrlAndChatId(chatId, linkUrl).isEmpty()) {
            log.info("link {} is already tracked in chat {}", linkUrl, chatId);
            throw new LinkIsAlreadyTrackedException();
        }
        log.info("add link {} to chat {}", linkUrl, chatId);
        linkRepository.addChatLink(chatId, link);
    }

    @Override
    public void remove(long chatId, URI url) {
        if (linkRepository.getChatLinksByUrlAndChatId(chatId, url.toString()).isEmpty()) {
            log.info("cant untrack untracked link {}", url);
            throw new InvalidUntrackLinkException();
        }
        ChatLink link = linkRepository.getChatLinksByUrlAndChatId(chatId, url.toString()).get(0);
        log.info("delete chat {}", chatId);
        linkRepository.removeChatLink(link);
        if(linkRepository.getChatLinksByLinkId(link.getLinkId()).isEmpty()) {
            linkRepository.removeLinkById(link.getLinkId());
        }
    }

    @Override
    public List<Link> listAll(long chatId) {
        log.info("service links in chat {}", chatId);
        return linkRepository.findAllLinksInChat(chatId);
    }
}
