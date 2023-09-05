package ru.tinkoff.edu.java.linkParser.handler;

import ru.tinkoff.edu.java.linkParser.link.ParsedLink;
import ru.tinkoff.edu.java.linkParser.link.StackOverflowParsedLink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StackOverflowHandler extends AbstractLinkHandler {

    private static final Pattern STACKOVERFLOW_PATTERN = Pattern.compile("https://stackoverflow.com/questions/(\\d+)(.*)");

    public StackOverflowHandler(LinkHandler handler) {
        super(handler);
    }

    @Override
    public ParsedLink parseLink(String url) {
        Matcher matcher = STACKOVERFLOW_PATTERN.matcher(url);
        return (matcher.matches() ? new StackOverflowParsedLink(matcher.group(1)) : nextLinkHandler.parseLink(url));
    }

}
