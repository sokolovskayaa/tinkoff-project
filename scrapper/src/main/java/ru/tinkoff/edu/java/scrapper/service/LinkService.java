package ru.tinkoff.edu.java.scrapper.service;

//import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;

import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;

import java.net.URI;
import java.util.List;

public interface LinkService {
    void add(long tgChatId, URI url);

    void remove(long tgChatId, URI url);

    List<Link> listAll(long tgChatId);
}
