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
where city.id = ?;

select country.id, country.name, city.id, city.name, airport.id, airport.name
from country join city on country.id = city.fk_country_id
             join airport on city.id = airport.fk_city_id
where country.id = ?;

insert into airport(fk_city_id, name) values (2, 'Horania Centralnaja');