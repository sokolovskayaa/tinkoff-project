package ru.tinkoff.edu.java.linkParser.parser;

import ru.tinkoff.edu.java.linkParser.builder.HandlerBuilder;
import ru.tinkoff.edu.java.linkParser.handler.LinkHandler;
import ru.tinkoff.edu.java.linkParser.link.ParsedLink;

public class LinkParser {
    private final LinkHandler linkHandler;

    public LinkParser() {
        HandlerBuilder builder = new HandlerBuilder();
        linkHandler = builder.getChainOfHandlers();
    }
    public ParsedLink parseLink(String url) {
        return linkHandler.parseLink(url);
    }
}
