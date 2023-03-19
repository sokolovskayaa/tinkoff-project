package ru.tinkoff.edu.java.linkParser.handler;

import ru.tinkoff.edu.java.linkParser.link.ParsedLink;

public interface LinkHandler {
    ParsedLink parseLink(String url);
}
