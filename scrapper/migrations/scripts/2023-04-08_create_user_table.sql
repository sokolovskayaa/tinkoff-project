-- liquibase formatted sql

--changeset sokolovskaya: add_user_table

create table user
(
    id bigint not null primary key
);