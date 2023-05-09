package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.dto.repository.ILink;

import java.net.URI;
import java.util.List;

public interface LinkService {
    void add(long tgChatId, URI url);

    void remove(long tgChatId, URI url);

    List<? extends ILink> listAll(long tgChatId);
}
