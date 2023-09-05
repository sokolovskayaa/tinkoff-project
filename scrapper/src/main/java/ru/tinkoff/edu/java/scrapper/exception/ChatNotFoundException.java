package ru.tinkoff.edu.java.scrapper.exception;

public class ChatNotFoundException extends RuntimeException {
    private static final String MESSAGE = "You should register before unregister";

    public ChatNotFoundException() {
        super(MESSAGE);
    }
}
