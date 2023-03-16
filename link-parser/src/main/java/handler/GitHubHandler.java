package handler;

import link.GitHubParsedLink;
import link.ParsedLink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GitHubHandler extends AbstractLinkHandler {

    public GitHubHandler(LinkHandler handler) {
        super(handler);
    }
    private final Pattern GITHUB_PATTERN = Pattern.compile("https://github.com/" +
                                                                 "([a-zA-Z\\d](?:[a-z\\d]|-(?=[a-zA-Z\\d])){0,38})/" +
                                                                 "([\\w.-]+)/?");

    @Override
    public ParsedLink parseLink(String url) {
        Matcher matcher = GITHUB_PATTERN.matcher(url);
        if (matcher.matches()) {
            return new GitHubParsedLink(matcher.group(1), matcher.group(2));
        } else {
            return nextLinkHandler.parseLink(url);
        }
    }
}
