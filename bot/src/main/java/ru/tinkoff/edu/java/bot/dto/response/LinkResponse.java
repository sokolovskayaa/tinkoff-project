package ru.tinkoff.edu.java.bot.dto.response;

import java.net.URI;

public record LinkResponse(
        long id,
        URI url
) {
    @Override
    public String toString() {
        return url.toString();
    }
}
