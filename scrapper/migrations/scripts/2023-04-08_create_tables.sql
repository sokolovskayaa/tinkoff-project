-- liquibase formatted sql

--changeset sokolovskaya:add_link_table
CREATE SEQUENCE link_id_seq;
create table link
(
    id         bigint default NEXTVAL('link_id_seq') not null primary key,
    url        text   not null unique,
    updated_at timestamp with time zone default now() not null
);

--changeset sokolovskaya:add_chat_table
create table chat
(
    id bigint not null primary key
);

--changeset sokolovskaya:add_chat_link_table
create table chat_link
(
    chat_id bigint,
    link_id bigint,
    primary key(chat_id, link_id),
    FOREIGN KEY(chat_id) REFERENCES chat(id),
    FOREIGN KEY(link_id) REFERENCES link(id)
);