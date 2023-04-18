package ru.tinkoff.edu.java.scrapper.exception;

public class ChatAlreadyExistsException extends RuntimeException {
    private static final String message = "User already register";

    public ChatAlreadyExistsException() {
        super(message);
    }
}
