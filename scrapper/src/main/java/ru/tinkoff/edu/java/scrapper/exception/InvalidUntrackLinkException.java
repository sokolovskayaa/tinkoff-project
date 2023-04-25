package ru.tinkoff.edu.java.scrapper.exception;

public class InvalidUntrackLinkException extends RuntimeException {
    private static final String message = "You should track link before untrack it.";

    public InvalidUntrackLinkException() {
        super(message);
    }
}
