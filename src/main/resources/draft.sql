drop schema if exists piskunou cascade;

set schema 'public';

drop table if exists flight;
drop table if exists "user";

create schema if not exists piskunou;
set schema 'piskunou';

drop table if exists "user";

create table if not exists "user"(
    id bigserial primary key,
    username varchar(50) unique not null
);

insert into piskunou."user"(username) values ('Bordan');

select * from piskunou."user";

create table if not exists piskunou.country(
    id bigserial primary key,
    "name" varchar(50) not null unique
);

create table if not exists piskunou.city(
    id bigserial primary key,
    fk_country_id bigint not null references piskunou.country(id) on delete cascade on update no action,
    "name" varchar(50) not null unique
);

select piskunou.country.name from piskunou.country;

set schema 'piskunou';

select id, name from country;

select *
from piskunou.city join piskunou.country on city.fk_country_id = country.id
where country.id = 1;

set schema 'piskunou';

select id, name from city;

insert into country(name) values('Belarus');
insert into country(name) values('Russia');
insert into country(name) values('Ukraine');

insert into city(fk_country_id, name) values (1, 'Miensk');
insert into city(fk_country_id, name) values (1, 'Horadnia');
insert into city(fk_country_id, name) values (1, 'Bierascie');

select country.id, country.name, city.id, city.name
from country join city on country.id = city.fk_country_id
where country.id = ?;

create table if not exists airport(
    id bigserial primary key,
    fk_city_id bigint not null references city(id) on delete cascade on update no action,
    "name" varchar(200) not null unique
);

insert into airport(fk_city_id, name) values (1, 'Miensk Zahodni');
insert into airport(fk_city_id, name) values (1, 'Miensk Ushodni');
insert into airport(fk_city_id, name) values (1, 'Miensk Centralny');

select city.id, city.name, airport.id, airport.name
from city join airport on city.id = airport.fk_city_id
where city.id in (?);

select country.id, country.name, city.id, city.name, airport.id, airport.name
from country join city on country.id = city.fk_country_id
             join airport on city.id = airport.fk_city_id
where country.id = ?;

insert into airport(fk_city_id, name) values (2, 'Horania Centralnaja');

select city.id, city.name, airport.id, airport.name
from city join airport on city.id = airport.fk_city_id;

create table if not exists airplane(
    id bigserial primary key,
    model varchar(300) not null unique,
    row_seat_no smallint not null,
    row_no smallint not null
);

alter table airplane rename column row_seat_no to seats_in_row;

create table if not exists airline(
    id bigserial primary key,
    "name" varchar(50) not null unique
);

insert into airline(name) values ('Belavia');
insert into airline(name) values ('Pobeda');
insert into airline(name) values ('Airflot');

insert into airplane(model, row_seat_no, row_no) values ('Boenig 737 Max', 6, 30);
insert into airplane(model, row_seat_no, row_no) values ('Boenig 747-8', 10, 50);
insert into airplane(model, row_seat_no, row_no) values ('Boenig 777', 4, 30);

create table if not exists flight(
    id bigserial primary key,
    fk_airport_from_id bigint not null references airport(id) on delete restrict on update no action,
    fk_airport_to_id bigint not null references airport(id) on delete restrict on update no action,
    fk_airline_id bigint not null references airline(id) on delete restrict on update no action,
    fk_airplane_id bigint not null references airplane(id) on delete restrict on update no action,
    departure_time timestamp not null,
    arrival_time timestamp not null,
    free_seats jsonb
);

select * from airport;
select * from airline;
select * from airplane;

select * from flight;

alter table flight add column price decimal(8,2);

update flight set price = 160 where id = 9;

select flight.id as flight_id,

       airport_from.id as airport_from_id,
       airport_from.name as airport_from_name,

       airport_to.id as airport_to_id,
       airport_to.name as airport_to_name,

       airplane.id as airplane_id,
       airplane.model as airplane_model,
       airplane.seats_in_row as airplane_seats_in_row,
       airplane.row_no as airplane_row_no,

       airline.id as airline_id,
       airline.name as airline_name,

       flight.departure_time as flight_departure_time,
       flight.arrival_time as flight_arrival_time,
       flight.price as flight_price
from flight inner join airport airport_from on flight.fk_airport_from_id = airport_from.id
     inner join airport airport_to on flight.fk_airport_to_id = airport_to.id
     inner join airline on flight.fk_airline_id = airline.id
     inner join airplane on flight.fk_airplane_id = airplane.id
where flight.id = 9;

select flight.id as flight_id,
       flight.departure_time as flight_departure_time,
       flight.arrival_time as flight_arrival_time,
       flight.price as flight_price,

       airport_from.id as airport_from_id,
       airport_from.name as airport_from_id,

       airport_to.id as airport_to_id,
       airport_to.name as airport_to_id,

       airline.id as airline_id,
       airline.name as airline_name

from flight inner join airport airport_from on flight.fk_airport_from_id = airport_from.id
            inner join airport airport_to on flight.fk_airport_to_id = airport_to.id
            inner join airline on flight.fk_airline_id = airline.id
where airport_from.id = 1 and airport_to.id = 4 and
      flight.departure_time between ? and ?;

select flight.id, free_seats from flight where flight.id = 9;

select jsonb_path_query_array(flight.free_seats, free_seats->'number') as free_seats
from flight where flight.id = 9;

select jsonb_path_query_array(flight.free_seats, '$[*] ? (@.free == true)') as free_seats
from flight where flight.id = 9;

truncate table flight;

update flight f_1 set free_seats = tmp.json_array
from (
    select f.id, jsonb_agg(
        case
            when e ->> 'number' !~  then jsonb_set(e, '{free}', 'true')
            else e
        end
    ) as json_array
    from flight f
    cross join jsonb_array_elements(free_seats) e
    group by 1
) as tmp
where f_1.id = 18;
select flight.id, free_seats from flight;

update flight set free_seats = jsonb_set(flight.free_seats, '{2, free}', 'true');