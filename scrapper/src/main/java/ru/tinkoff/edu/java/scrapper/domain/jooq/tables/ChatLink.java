/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.Keys;
import ru.tinkoff.edu.java.scrapper.domain.jooq.Public;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.ChatLinkRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ChatLink extends TableImpl<ChatLinkRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.chat_link</code>
     */
    public static final ChatLink CHAT_LINK = new ChatLink();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChatLinkRecord> getRecordType() {
        return ChatLinkRecord.class;
    }

    /**
     * The column <code>public.chat_link.chat_id</code>.
     */
    public final TableField<ChatLinkRecord, Long> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.chat_link.link_id</code>.
     */
    public final TableField<ChatLinkRecord, Long> LINK_ID = createField(DSL.name("link_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private ChatLink(Name alias, Table<ChatLinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private ChatLink(Name alias, Table<ChatLinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.chat_link</code> table reference
     */
    public ChatLink(String alias) {
        this(DSL.name(alias), CHAT_LINK);
    }

    /**
     * Create an aliased <code>public.chat_link</code> table reference
     */
    public ChatLink(Name alias) {
        this(alias, CHAT_LINK);
    }

    /**
     * Create a <code>public.chat_link</code> table reference
     */
    public ChatLink() {
        this(DSL.name("chat_link"), null);
    }

    public <O extends Record> ChatLink(Table<O> child, ForeignKey<O, ChatLinkRecord> key) {
        super(child, key, CHAT_LINK);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ChatLinkRecord> getPrimaryKey() {
        return Keys.CHAT_LINK_PKEY;
    }

    @Override
    public List<ForeignKey<ChatLinkRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CHAT_LINK__CHAT_LINK_CHAT_ID_FKEY, Keys.CHAT_LINK__CHAT_LINK_LINK_ID_FKEY);
    }

    private transient Chat _chat;
    private transient Link _link;

    /**
     * Get the implicit join path to the <code>public.chat</code> table.
     */
    public Chat chat() {
        if (_chat == null)
            _chat = new Chat(this, Keys.CHAT_LINK__CHAT_LINK_CHAT_ID_FKEY);

        return _chat;
    }

    /**
     * Get the implicit join path to the <code>public.link</code> table.
     */
    public Link link() {
        if (_link == null)
            _link = new Link(this, Keys.CHAT_LINK__CHAT_LINK_LINK_ID_FKEY);

        return _link;
    }

    @Override
    public ChatLink as(String alias) {
        return new ChatLink(DSL.name(alias), this);
    }

    @Override
    public ChatLink as(Name alias) {
        return new ChatLink(alias, this);
    }

    @Override
    public ChatLink as(Table<?> alias) {
        return new ChatLink(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatLink rename(String name) {
        return new ChatLink(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatLink rename(Name name) {
        return new ChatLink(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatLink rename(Table<?> name) {
        return new ChatLink(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
