--liquibase formatted sql

--changeset mykyda:1
create table auctions(
    id bigserial primary key,
    name varchar(50) not null,
    description varchar(200),
    photo bytea,
    author_id bigint not null references users(id),
    contacts varchar(200) not null,
    current_bid bigint not null,
    bidder_id bigint not null references users(id),
    expire_time timestamp not null,
    expire_date date not null
);
--changeset mykyda:2
create table users(
    id bigserial primary key,
    name varchar(50) not null
);
-- changeset mykyda:3
insert into users(name) values
                        (
                            ("testuser1"),(testuser2),(testuser3),(testuser4)
                        );

