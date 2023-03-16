import parser.LinkParser;

public class Main {
    public static void main(String[] args) {
        String url = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
        LinkParser parser = new LinkParser();
        System.out.println(parser.parseLink(url));
    }
}
