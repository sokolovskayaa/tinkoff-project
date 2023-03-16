package handler;

public class HandlerBuilder {

    private LinkHandler linkHandler;

    public LinkHandler getChainOfHandlers() {
        return new GitHubHandler(new StackOverflowHandler(new UnsupportedLinkHandler(null)));
    }


}
