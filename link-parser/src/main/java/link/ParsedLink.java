package link;

public sealed interface ParsedLink permits GitHubParsedLink, StackOverflowParsedLink, UnsupportedParsedLink {
    String toString();
}
