package handler;

import link.ParsedLink;
import link.UnsupportedParsedLink;

public final class UnsupportedLinkHandler extends AbstractLinkHandler {
    public UnsupportedLinkHandler(AbstractLinkHandler handler) {
        super(handler);
    }

    @Override
    public ParsedLink parseLink(String url) {
        return new UnsupportedParsedLink(null);
    }
}
