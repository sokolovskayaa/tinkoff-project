package ru.tinkoff.edu.java.bot.enums;

public enum Command {
    START("/start", "register new user"),
    HELP("/help", """
                         list of commands:
                         /start -- register new user
                         /list -- show list of tracked links
                         /track link -- start to track link
                         /untrack link -- stop to track link
                         """),
    LIST("/list", "show list of tracked links"),
    EMPTY_LIST("/list", "no tracked links"),
    TRACK("/track", "track link"),
    UNTRACK("/untrack", "untrack link"),
    UNSUPPORTED(null, "unsupported command");
    public final String command;
    public final String description;

    Command(String command, String description) {
        this.description = description;
        this.command = command;

    }
}
