--liquibase formatted sql

--changeset mykyda:1
create table funds(
    id bigserial primary key,
    name varchar(50) not null,
    value float not null
);
--changeset mykyda:2
create table auctions(
                    id bigserial primary key,
                    name varchar(50) not null,
                    description varchar(200),
                    photo text not null,
                    author_name bigint not null,
                    contacts varchar(200) not null,
                    expire_time timestamp not null,
                    fund_id bigint not null references funds(id),
                    fund_stake float not null
);
-- changeset mykyda:3
create table bids(
                     id bigserial primary key,
                     bidder_name bigint not null,
                     amount float not null,
                     auction_id bigint not null references auctions(id)
);


