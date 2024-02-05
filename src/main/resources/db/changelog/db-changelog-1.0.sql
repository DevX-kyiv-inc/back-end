--liquibase formatted sql

--changeset acheron:1
create table store
(
    id         bigserial primary key ,
    name   text unique not null

);