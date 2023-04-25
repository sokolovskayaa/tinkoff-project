/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Chat;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.ChatLink;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.chat</code>.
     */
    public final Chat CHAT = Chat.CHAT;

    /**
     * The table <code>public.chat_link</code>.
     */
    public final ChatLink CHAT_LINK = ChatLink.CHAT_LINK;

    /**
     * The table <code>public.link</code>.
     */
    public final Link LINK = Link.LINK;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Chat.CHAT,
            ChatLink.CHAT_LINK,
            Link.LINK
        );
    }
}