package handler;

public abstract class AbstractLinkHandler implements LinkHandler{

    protected LinkHandler nextLinkHandler;

    AbstractLinkHandler(LinkHandler linkHandler) {
        nextLinkHandler = linkHandler;
    }

}
