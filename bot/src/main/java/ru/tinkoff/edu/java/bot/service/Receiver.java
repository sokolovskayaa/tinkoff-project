package ru.tinkoff.edu.java.bot.service;

import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

public interface Receiver {
    void updateLinks(LinkUpdateRequest request);
}
