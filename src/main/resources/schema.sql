create schema if not exists piskunou;
set schema 'piskunou';

create table if not exists "user"(
    id bigserial primary key,
    username varchar(50) unique not null,
    authority varchar(10) not null
);

create table if not exists flight(
    id bigserial primary key
);

create table if not exists passenger(
    id bigserial primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    date_of_birth date not null,
    age varchar(10) not null,
    sex varchar(6) not null
);

create table if not exists passport(
    fk_passenger_id bigint primary key references passenger(id) on delete cascade on update no action,
    number varchar(20) not null,
    identification_id varchar(20) not null
);

create table if not exists ticket(
    id bigserial primary key,
    fk_user_id bigint references user(id) on delete cascade on update no action,
    fk_flight_id bigint references flight(id) on delete cascade on update no action,
    fk_passenger_id bigint not null references passenger(id) on delete cascade on update no action,
    price decimal(8, 2) not null,
    seat_number varchar(5) not null,
    ticket_type varchar(10) not null
);

create table if not exists dual_ticket(
    fk_user_id bigint primary key references "user"(id),
    fk_there_ticket_id bigint references ticket(id),
    fk_back_ticket_id bigint null references ticket(id)
);

create table if not exists airplane(
    id bigserial primary key,
    model varchar(300) not null unique,
    seat_amount smallint not null
);