package ru.tinkoff.edu.java.linkParser.link;

public record GitHubParsedLink(String id, String repo) implements ParsedLink{
    @Override
    public String toString() {
        return id + "/" + repo;
    }
}
