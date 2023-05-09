package ru.tinkoff.edu.java.scrapper.exception;

public class ChatAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "User already register";

    public ChatAlreadyExistsException() {
        super(MESSAGE);
    }
}
