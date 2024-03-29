/*
 * This file is generated by jOOQ.
 */

package ru.tinkoff.edu.java.scrapper.domain.jooq.tables;

import java.util.function.Function;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function1;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row1;
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
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.ChatRecord;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Chat extends TableImpl<ChatRecord> {

    /**
     * The reference instance of <code>public.chat</code>
     */
    public static final Chat CHAT = new Chat();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>public.chat.id</code>.
     */
    public final TableField<ChatRecord, Long> ID =
        createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    private Chat(Name alias, Table<ChatRecord> aliased) {
        this(alias, aliased, null);
    }

    private Chat(Name alias, Table<ChatRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.chat</code> table reference
     */
    public Chat(String alias) {
        this(DSL.name(alias), CHAT);
    }

    /**
     * Create an aliased <code>public.chat</code> table reference
     */
    public Chat(Name alias) {
        this(alias, CHAT);
    }

    /**
     * Create a <code>public.chat</code> table reference
     */
    public Chat() {
        this(DSL.name("chat"), null);
    }

    public <O extends Record> Chat(Table<O> child, ForeignKey<O, ChatRecord> key) {
        super(child, key, CHAT);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChatRecord> getRecordType() {
        return ChatRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ChatRecord> getPrimaryKey() {
        return Keys.CHAT_PKEY;
    }

    @Override
    public Chat as(String alias) {
        return new Chat(DSL.name(alias), this);
    }

    @Override
    public Chat as(Name alias) {
        return new Chat(alias, this);
    }

    @Override
    public Chat as(Table<?> alias) {
        return new Chat(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Chat rename(String name) {
        return new Chat(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Chat rename(Name name) {
        return new Chat(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Chat rename(Table<?> name) {
        return new Chat(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<Long> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function1<? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function1<? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
