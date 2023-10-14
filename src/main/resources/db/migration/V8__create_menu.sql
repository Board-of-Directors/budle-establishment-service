create table menu(
    id bigserial,
    primary key (id)
);

alter table establishments
add menu_id bigint;

create table menu_category(
    id bigserial primary key,
    name varchar,
    parent_category_id bigint
);

create table menu_menu_category(
    menu_id bigint,
    menu_category_id bigint
);

create table menu_product(
    id bigserial,
    name varchar,
    description varchar,
    weightg varchar,
    price varchar,
    photo_id bigint,
    category_id bigint references menu_category(id)
)