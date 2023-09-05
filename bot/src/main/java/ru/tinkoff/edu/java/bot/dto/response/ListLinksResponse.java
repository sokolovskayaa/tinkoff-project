package ru.tinkoff.edu.java.bot.dto.response;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        int size
) {
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("");
        for (var to : links) {
            toString.append(to);
            toString.append("\n");
        }
        return toString.toString();
    }
}
