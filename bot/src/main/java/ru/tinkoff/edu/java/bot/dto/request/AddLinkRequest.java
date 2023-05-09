package ru.tinkoff.edu.java.bot.dto.request;

public record AddLinkRequest(
    String link
) {
    @Override
    public String toString() {
        return link;
    }
}
