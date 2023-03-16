package handler;

import link.ParsedLink;
import link.StackOverflowParsedLink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StackOverflowHandler extends AbstractLinkHandler {

    public StackOverflowHandler(AbstractLinkHandler handler) {
        super(handler);
    }

    private final Pattern STACKOVERFLOW_PATTERN = Pattern.compile("https://stackoverflow.com/questions/(\\d+)");

    @Override
    public ParsedLink parseLink(String url) {
        Matcher matcher = STACKOVERFLOW_PATTERN.matcher(url);
        if (matcher.matches()) {
            return new StackOverflowParsedLink(matcher.group(1));
        } else {
            return nextLinkHandler.parseLink(url);
        }
    }

}
