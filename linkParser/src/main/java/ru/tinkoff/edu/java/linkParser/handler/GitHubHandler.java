package ru.tinkoff.edu.java.linkParser.handler;

import ru.tinkoff.edu.java.linkParser.link.GitHubParsedLink;
import ru.tinkoff.edu.java.linkParser.link.ParsedLink;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GitHubHandler extends AbstractLinkHandler {
    private static final Pattern GITHUB_PATTERN = Pattern.compile("https://github.com/" +
        "([a-zA-Z\\d](?:[a-z\\d]|-(?=[a-zA-Z\\d])){0,38})/" +
        "([\\w.-]+)/?");

    public GitHubHandler(LinkHandler handler) {
        super(handler);
    }

    @Override
    public ParsedLink parseLink(String url) {
        Matcher matcher = GITHUB_PATTERN.matcher(url);
        return (matcher.matches() ? new GitHubParsedLink(matcher.group(1), matcher.group(2)) : nextLinkHandler.parseLink(url));
    }
}
