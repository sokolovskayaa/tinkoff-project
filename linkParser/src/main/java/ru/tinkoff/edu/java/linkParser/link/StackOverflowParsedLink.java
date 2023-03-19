package ru.tinkoff.edu.java.linkParser.link;

public record StackOverflowParsedLink(String id) implements ParsedLink {
    @Override
    public String toString() {
        return id;
    }
}
