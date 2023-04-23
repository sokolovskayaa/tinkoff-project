package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkParser.link.ParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;
import ru.tinkoff.edu.java.linkParser.parser.LinkParser;
import ru.tinkoff.edu.java.scrapper.dto.repository.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.Link;
import ru.tinkoff.edu.java.scrapper.exception.InvalidTrackLinkException;
import ru.tinkoff.edu.java.scrapper.exception.InvalidUntrackLinkException;
import ru.tinkoff.edu.java.scrapper.exception.LinkIsAlreadyTrackedException;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class JdbcLinkService implements LinkService {

    private final JdbcLinkRepository linkRepository;
    private final LinkParser linkParser = new LinkParser();

    @Override
    public void add(long chatId, URI url) {
        String linkUrl = url.toString();
        ParsedLink parsedLink = linkParser.parseLink(linkUrl);
        if (parsedLink instanceof UnsupportedParsedLink) {
            log.info("cant track link {}", linkUrl);
            throw new InvalidTrackLinkException();
        }
        if(linkRepository.getLinksFromLinkByUrl(linkUrl).isEmpty()) {
            linkRepository.addLink(linkUrl);
        }
        Link link = linkRepository.getLinksFromLinkByUrl(linkUrl).get(0);
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
            linkRepository.removeLastLink(link.getLinkId());
        }
    }

    @Override
    public List<Link> listAll(long chatId) {
        log.info("service links in chat {}", chatId);
        return linkRepository.findAllLinksInChat(chatId);
    }
}
