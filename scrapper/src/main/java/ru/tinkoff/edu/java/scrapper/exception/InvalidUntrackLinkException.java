package ru.tinkoff.edu.java.scrapper.exception;

public class InvalidUntrackLinkException extends RuntimeException {
    public InvalidUntrackLinkException() {
        super("You should track link before untrack it.");
    }
}
