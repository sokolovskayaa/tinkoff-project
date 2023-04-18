package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkParser.link.ParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;
import ru.tinkoff.edu.java.linkParser.parser.LinkParser;
import ru.tinkoff.edu.java.scrapper.dto.repository.Link;
import ru.tinkoff.edu.java.scrapper.exception.InvalidTrackLinkException;
import ru.tinkoff.edu.java.scrapper.exception.InvalidUntrackLinkException;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JdbcLinkService implements LinkService {

    private final JdbcLinkRepository linkRepository;
    private final LinkParser linkParser = new LinkParser();

    @Override
    public Link add(long chatId, URI url) {
        String link = url.toString();
        ParsedLink parsedLink = linkParser.parseLink(link);
        if (parsedLink instanceof UnsupportedParsedLink) {
            log.info("cant track link {}", link);
            throw new InvalidTrackLinkException();
        } else {
            log.info("add link {} to chat {}", link, chatId);
            return linkRepository.add(chatId, link);
        }
    }

    @Override
    public Link remove(long chatId, URI url) {
        if (!linkRepository.exists(chatId, url.toString())) {
            log.info("cant untrack untracked link {}", url.toString());
            throw new InvalidUntrackLinkException();
        } else {
            log.info("delete chat {}", chatId);
            return linkRepository.remove(chatId, url.toString());
        }
    }

    @Override
    public List<Link> listAll(long chatId) {
        log.info("service links in chat {}", chatId);
        return linkRepository.findAll(chatId);
    }
}
