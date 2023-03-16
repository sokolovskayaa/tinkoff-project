package link;

public record StackOverflowParsedLink(String id) implements ParsedLink {
    @Override
    public String toString() {
        return id;
    }
}
