package ru.tinkoff.edu.java.linkParser.handler;

public abstract class AbstractLinkHandler implements LinkHandler {

    protected LinkHandler nextLinkHandler;

    public AbstractLinkHandler(LinkHandler linkHandler) {
        nextLinkHandler = linkHandler;
    }

}
