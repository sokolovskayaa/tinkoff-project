package parser;

import handler.HandlerBuilder;
import handler.LinkHandler;
import link.ParsedLink;

public class LinkParser {
    public HandlerBuilder builder = new HandlerBuilder();

    public ParsedLink parseLink(String url) {
        LinkHandler linkHandler = builder.getChainOfHandlers();
        return linkHandler.parseLink(url);
    }
}
