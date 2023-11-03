create table review
(
    id               bigserial primary key,
    establishment_id bigint references establishments (id),
    score            int    not null,
    text             text,
    user_id          bigint not null,
    date             date not null
)