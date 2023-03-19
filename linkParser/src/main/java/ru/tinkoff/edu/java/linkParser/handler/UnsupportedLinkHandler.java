package ru.tinkoff.edu.java.linkParser.handler;

import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;
import ru.tinkoff.edu.java.linkParser.link.ParsedLink;

public final class UnsupportedLinkHandler extends AbstractLinkHandler {
    public UnsupportedLinkHandler(AbstractLinkHandler handler) {
        super(handler);
    }

    @Override
    public ParsedLink parseLink(String url) {
        return new UnsupportedParsedLink(null);
    }
}
