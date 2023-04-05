package ru.tinkoff.edu.java.linkParser.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tinkoff.edu.java.linkParser.link.GitHubParsedLink;
import ru.tinkoff.edu.java.linkParser.link.StackOverflowParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;

import java.util.stream.Stream;

public class LinkParserTest {
    private final LinkParser parser = new LinkParser();

    @ParameterizedTest
    @MethodSource("supportedGitHubLinks")
    public void testSupportedGitHubLinks(String link, String id, String repo) {
        Assertions.assertEquals(parser.parseLink(link), new GitHubParsedLink(id, repo));
    }

    private static Stream<Arguments> supportedGitHubLinks() {
        return Stream.of(
                Arguments.of("https://github.com/danielpalme/ReportGenerator", "danielpalme", "ReportGenerator"),
                Arguments.of("https://github.com/justlel/tgbot-handlers-integration", "justlel", "tgbot-handlers-integration"),
                Arguments.of("https://github.com/sokolovskayaa/tinkoff-project", "sokolovskayaa", "tinkoff-project")
        );
    }

    @ParameterizedTest
    @MethodSource("supportedStackOverflowLinks")
    public void testSupportedStackOverflowLinks(String link, String id) {
        Assertions.assertEquals(parser.parseLink(link), new StackOverflowParsedLink(id));
    }

    private static Stream<Arguments> supportedStackOverflowLinks() {
        return Stream.of(
                Arguments.of("https://stackoverflow.com/questions/75898894/is-it-possible-to-create-an-instance-from-a-generic-type", "75898894"),
                Arguments.of("https://stackoverflow.com/questions/75916782/whether-sqoop-can-import-multiple-specific-tables-at-once", "75916782"),
                Arguments.of("https://stackoverflow.com/questions/48/multiple-submit-buttons-in-an-html-form", "48")
        );
    }

    @ParameterizedTest
    @MethodSource("unsupportedLinks")
    public void testUnsupportedLinks(String link, String nullStr) {
        Assertions.assertEquals(parser.parseLink(link), new UnsupportedParsedLink(nullStr));
    }

    private static Stream<Arguments> unsupportedLinks() {
        return Stream.of(
                Arguments.of("https://www.youtube.com/watch?v=rpCi1h4kWO8", null),
                Arguments.of("https://stackoverflow.com/users/5734961/dimava", null),
                Arguments.of("https://gist.github.com/sanyarnd/52a78a01fa9ec234c8ad50fbc5ecc9e4", null)
        );
    }
}
