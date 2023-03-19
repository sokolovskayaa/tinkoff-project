package parser;

import handler.HandlerBuilder;
import handler.LinkHandler;
import link.ParsedLink;

public class LinkParser {
    public HandlerBuilder builder = new HandlerBuilder();
    LinkHandler linkHandler = builder.getChainOfHandlers();


    public ParsedLink parseLink(String url) {
        return linkHandler.parseLink(url);
    }
}
