create schema if not exists piskunou;
set schema 'piskunou';

create table if not exists "user"(
    id bigserial primary key,
    username varchar(50) not null unique
);

create table if not exists airplane(
    id bigserial primary key,
    model varchar(300) not null unique,
    row_seat_no smallint not null,
    row_no smallint not null
);

create table if not exists country(
    id bigserial primary key,
    "name" varchar(50) not null unique
);

create table if not exists city(
    id bigserial primary key,
    fk_country_id bigint not null references country(id) on delete cascade on update no action,
    "name" varchar(50) not null unique
);

create table if not exists airport(
    id bigserial primary key,
    fk_city_id bigint not null references city(id) on delete cascade on update no action,
    "name" varchar(200) not null unique
);

create table if not exists airline(
    id bigserial primary key,
    "name" varchar(50) not null unique
);

create table if not exists flight(
    id bigserial primary key,
    fk_airport_from_id bigint not null references airport(id) on delete restrict on update no action,
    fk_airport_to_id bigint not null references airport(id) on delete restrict on update no action,
    fk_airline_id bigint not null references airline(id) on delete restrict on update no action,
    fk_airplane_id bigint not null references airplane(id) on delete restrict on update no action,
    departure_time timestamp not null,
    arrival_time timestamp not null,
    price decimal(8, 2) not null,
    free_seats jsonb
);

create table if not exists passenger(
    id bigserial primary key,
    fk_country_id bigint references country(id) on delete set null on update no action,
    firstname varchar(50) not null,
    surname varchar(50) not null,
    date_of_birth date not null,
    age varchar(10) not null,
    sex varchar(6) not null
);

create table if not exists passport(
    fk_passenger_id bigint primary key references passenger(id) on delete cascade on update no action,
    number varchar(20) not null unique,
    identification_no varchar(20) not null unique
);

create table if not exists ticket(
    id bigserial primary key,
    fk_owner_id bigint references user(id) on delete set null on update no action,
    fk_flight_id bigint not null references flight(id) on delete cascade on update no action,
    fk_passenger_id bigint not null references passenger(id) on delete cascade on update no action,
    price decimal(8, 2) not null,
    seat_no varchar(5) not null
);