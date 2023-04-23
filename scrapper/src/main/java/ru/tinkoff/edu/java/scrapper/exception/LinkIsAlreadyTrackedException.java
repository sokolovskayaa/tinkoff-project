package ru.tinkoff.edu.java.scrapper.exception;

public class LinkIsAlreadyTrackedException extends RuntimeException {
    private static final String message = "This link is already tracked.";

    public LinkIsAlreadyTrackedException() {
        super(message);
    }
}
