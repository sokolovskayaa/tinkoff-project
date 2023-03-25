package ru.tinkoff.edu.java.scrapper.exception;

public class ChatNotFoundException extends Exception {
    public ChatNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
