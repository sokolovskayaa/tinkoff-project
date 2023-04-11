-- liquibase formatted sql

--changeset sokolovskaya: add_user_link_table

create table user_link
(
    user_id bigint FOREIGN KEY REFERENCES user(id),
    link_id bigint FOREIGN KEY REFERENCES link(id),
    primary key(user_id, link_id)
);