package ru.tinkoff.edu.java.scrapper.exception;

public class InvalidTrackLinkException extends RuntimeException{
    private static final String message = "I can track GitHub repositories and Stackoverflow questions only.";

    public InvalidTrackLinkException() {
        super(message);
    }
}
