DROP TABLE IF EXISTS users cascade;



DROP TABLE IF EXISTS establishments cascade;
create table establishments
(

    id               bigserial,
    name             varchar(100) NOT NULL,
    description      varchar      not null,
    address          varchar      not null,
    has_map          boolean default false,
    has_card_payment boolean default false,
    price            int4         not null,
    rating           float        not null,
    image            varchar      not null,
    map              varchar,
    category         varchar      not null,
    stars_count      integer,
    cuisine_country  varchar,
    owner_id         bigint,
    primary key (id),
    unique ("name", address)
);

