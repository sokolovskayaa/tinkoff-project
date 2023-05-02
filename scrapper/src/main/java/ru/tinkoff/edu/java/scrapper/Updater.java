package ru.tinkoff.edu.java.scrapper;

import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdateRequest;

public interface Updater {
    void updateLink(LinkUpdateRequest request);
}
