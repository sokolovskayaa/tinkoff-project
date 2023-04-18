package ru.tinkoff.edu.java.scrapper.exception;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(){
        super("You should register before unregister");
    }
}
