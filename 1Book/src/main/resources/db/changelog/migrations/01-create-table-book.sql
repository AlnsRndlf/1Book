create table book (
    isbn bigint primary key,
    title varchar(255),
    author varchar(255),
    year_publicated int,
    sotck int not null
);