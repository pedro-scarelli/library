-- V1__create_books_table.sql
CREATE TABLE tb_books (
    pk_id UUID PRIMARY KEY,
    st_title VARCHAR(100) NOT NULL,
    st_author VARCHAR(255) NOT NULL,
    dt_year TIMESTAMP NOT NULL
);

CREATE INDEX ix_books_title ON tb_books(st_title);
CREATE INDEX ix_books_author ON tb_books(st_author);