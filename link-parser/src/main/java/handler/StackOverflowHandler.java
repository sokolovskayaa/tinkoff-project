package handler;

import link.ParsedLink;
import link.StackOverflowParsedLink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StackOverflowHandler extends AbstractLinkHandler {

    public StackOverflowHandler(AbstractLinkHandler handler) {
        super(handler);
    }

    private static final Pattern STACKOVERFLOW_PATTERN = Pattern.compile("https://stackoverflow.com/questions/(\\d+)");

    @Override
    public ParsedLink parseLink(String url) {
        Matcher matcher = STACKOVERFLOW_PATTERN.matcher(url);
        return (matcher.matches() ? new StackOverflowParsedLink(matcher.group(1)) : nextLinkHandler.parseLink(url));
    }

}
