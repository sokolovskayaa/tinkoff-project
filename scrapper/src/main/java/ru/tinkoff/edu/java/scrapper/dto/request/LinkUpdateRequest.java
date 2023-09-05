package ru.tinkoff.edu.java.scrapper.dto.request;

import java.util.List;

public record LinkUpdateRequest(
    long id,
    String url,
    String description,
    List<Long> tgChatIds
) {
}
