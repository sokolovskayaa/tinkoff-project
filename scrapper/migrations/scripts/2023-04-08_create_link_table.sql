-- liquibase formatted sql

--changeset sokolovskaya: add_link_table

CREATE SEQUENCE link_id_seq;
create table link
(
    id         bigint default NEXTVAL('link_id_seq') not null primary key,
    url        text   not null,
    updated_at date default now() not null

);