package ru.tinkoff.edu.java.linkParser.link;

public record UnsupportedParsedLink(String nullStr) implements ParsedLink {
    @Override
    public String toString() {
        return null;
    }
}
