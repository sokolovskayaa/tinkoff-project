package ru.tinkoff.edu.java.linkParser.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkParser.link.GitHubParsedLink;
import ru.tinkoff.edu.java.linkParser.link.StackOverflowParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;

public class LinkParserTest {
    private final LinkParser parser = new LinkParser();

    @Test
    public void testSupportedGitHubLinks() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(parser.parseLink("https://github.com/danielpalme/ReportGenerator"), new GitHubParsedLink("danielpalme", "ReportGenerator")),
                () -> Assertions.assertEquals(parser.parseLink("https://github.com/justlel/tgbot-handlers-integration"), new GitHubParsedLink("justlel", "tgbot-handlers-integration")),
                () -> Assertions.assertEquals(parser.parseLink("https://github.com/sokolovskayaa/tinkoff-project"), new GitHubParsedLink("sokolovskayaa", "tinkoff-project"))
        );


    }

    @Test
    public void testSupportedStackOverflowLinks() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(parser.parseLink("https://stackoverflow.com/questions/75898894/is-it-possible-to-create-an-instance-from-a-generic-type"), new StackOverflowParsedLink("75898894")),
                () -> Assertions.assertEquals(parser.parseLink("https://stackoverflow.com/questions/75916782/whether-sqoop-can-import-multiple-specific-tables-at-once"), new StackOverflowParsedLink("75916782")),
                () -> Assertions.assertEquals(parser.parseLink("https://stackoverflow.com/questions/48/multiple-submit-buttons-in-an-html-form"), new StackOverflowParsedLink("48"))
        );

    }

    @Test
    public void testUnsupportedLinks() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(parser.parseLink("https://www.youtube.com/watch?v=rpCi1h4kWO8"), new UnsupportedParsedLink(null)),
                () -> Assertions.assertEquals(parser.parseLink("https://stackoverflow.com/users/5734961/dimava"), new UnsupportedParsedLink(null)),
                () -> Assertions.assertEquals(parser.parseLink("https://gist.github.com/sanyarnd/52a78a01fa9ec234c8ad50fbc5ecc9e4"), new UnsupportedParsedLink(null))
        );
    }
}
