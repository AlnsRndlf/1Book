-- liquibase formatted sql
-- changeset alonso:1

create table book (
    isbn bigint primary key,
    title varchar(255),
    author varchar(255),
    year_publicated int,
    sotck int not null
);

-- changeset alonso:2

ALTER TABLE book
    CHANGE COLUMN sotck stock INT;

-- changeset alonso:3
alter table book
    modify column title varchar(255) not null,
    modify column author varchar(255) not null;