package handler;

import link.ParsedLink;

public interface LinkHandler {
    ParsedLink parseLink(String url);
}
