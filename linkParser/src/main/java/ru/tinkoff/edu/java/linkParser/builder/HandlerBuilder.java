package ru.tinkoff.edu.java.linkParser.builder;

import ru.tinkoff.edu.java.linkParser.handler.GitHubHandler;
import ru.tinkoff.edu.java.linkParser.handler.LinkHandler;
import ru.tinkoff.edu.java.linkParser.handler.StackOverflowHandler;
import ru.tinkoff.edu.java.linkParser.handler.UnsupportedLinkHandler;

public class HandlerBuilder {

    public LinkHandler getChainOfHandlers() {
        return new GitHubHandler(new StackOverflowHandler(new UnsupportedLinkHandler(null)));
    }


}
