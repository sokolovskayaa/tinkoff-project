package ru.tinkoff.edu.java.linkParser.handler;

import ru.tinkoff.edu.java.linkParser.link.ParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;

public final class UnsupportedLinkHandler extends AbstractLinkHandler {
    public UnsupportedLinkHandler(LinkHandler handler) {
        super(handler);
    }

    @Override
    public ParsedLink parseLink(String url) {
        return new UnsupportedParsedLink(null);
    }
}
