package ru.tinkoff.edu.java.scrapper.exception;

public class InvalidTrackLinkException extends RuntimeException{
    public InvalidTrackLinkException() {
        super("I can track GitHub repositories and Stackoverflow questions only.");
    }
}
