package ru.tinkoff.edu.java.linkParser.link;

public sealed interface ParsedLink permits GitHubParsedLink, StackOverflowParsedLink, UnsupportedParsedLink {
    String toString();
}
