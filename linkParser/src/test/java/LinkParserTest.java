import org.junit.Assert;
import org.junit.Test;
import ru.tinkoff.edu.java.linkParser.link.GitHubParsedLink;
import ru.tinkoff.edu.java.linkParser.link.StackOverflowParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;
import ru.tinkoff.edu.java.linkParser.parser.LinkParser;

import static org.hamcrest.Matchers.instanceOf;

public class LinkParserTest {
    private final LinkParser parser = new LinkParser();

    @Test
    public void testSupportedGitHubLinks() {
//        Assert.assertThat(parser.parseLink("https://github.com/danielpalme/ReportGenerator").getClass(),
//                                                                        instanceOf(GitHubParsedLink.class));
        Assert.assertEquals(parser.parseLink("https://github.com/danielpalme/ReportGenerator"),
                                                                new GitHubParsedLink("danielpalme", "ReportGenerator"));
    }

    @Test
    public void testSupportedStackOverflowLinks() {
//        Assert.assertThat(parser.parseLink("https://github.com/danielpalme/ReportGenerator").getClass(),
//                                                                        instanceOf(GitHubParsedLink.class));
        Assert.assertEquals(parser.parseLink("https://stackoverflow.com/questions/75898894/is-it-possible-to-create-an-instance-from-a-generic-type"),
                new StackOverflowParsedLink("75898894"));
    }

    @Test
    public void testUnsupportedLinks() {
//        Assert.assertThat(parser.parseLink("https://github.com/danielpalme/ReportGenerator").getClass(),
//                                                                        instanceOf(GitHubParsedLink.class));
        Assert.assertEquals(parser.parseLink("https://www.youtube.com/watch?v=rpCi1h4kWO8"),
                new UnsupportedParsedLink(null));
    }
}
