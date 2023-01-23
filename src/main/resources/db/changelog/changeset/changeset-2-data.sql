--liquibase formatted sql

--changeset cichan:insertion-into-users
insert into users(username) values ('Bogdan');
insert into users(username) values ('Cichan');
insert into users(username) values ('Dmitry');
insert into users(username) values ('Elizaveta');
insert into users(username) values ('Ilya');
insert into users(username) values ('Yaroslav');

--changeset cichan:insertion-into-airlplanes
insert into airplanes(model, seats_in_row, row_no) values ('Boeing Next-Genetation 737', 6, 30);
insert into airplanes(model, seats_in_row, row_no) values ('Boeing 737 MAX', 10, 50);
insert into airplanes(model, seats_in_row, row_no) values ('Boeing 747-8', 4, 30);
insert into airplanes(model, seats_in_row, row_no) values ('Boeing 767', 6, 30);
insert into airplanes(model, seats_in_row, row_no) values ('Boeing 777', 6, 30);
insert into airplanes(model, seats_in_row, row_no) values ('Boening 777X', 6, 35);
insert into airplanes(model, seats_in_row, row_no) values ('Boening 787', 10, 40);
insert into airplanes(model, seats_in_row, row_no) values ('A220', 4, 25);
insert into airplanes(model, seats_in_row, row_no) values ('A320', 6, 32);
insert into airplanes(model, seats_in_row, row_no) values ('A330', 6, 31);
insert into airplanes(model, seats_in_row, row_no) values ('A350', 6, 31);
insert into airplanes(model, seats_in_row, row_no) values ('A380', 10, 40);

--changeset cichan:insertion-into-countries
insert into countries(name) values('Belarus');
insert into countries(name) values('Russia');
insert into countries(name) values('Ukraine');
insert into countries(name) values('Poland');
insert into countries(name) values('German');
insert into countries(name) values('United Arab Emirates');

--changeset cichan:insertion-into-cities
insert into cities(fk_country_id, name) values (1, 'Miensk');
insert into cities(fk_country_id, name) values (1, 'Horadnia');
insert into cities(fk_country_id, name) values (1, 'Bierascie');
insert into cities(fk_country_id, name) values (2, 'Moscow');
insert into cities(fk_country_id, name) values (2, 'St.Petersburg');
insert into cities(fk_country_id, name) values (2, 'Yekaterinburg');
insert into cities(fk_country_id, name) values (3, 'Kiev');
insert into cities(fk_country_id, name) values (3, 'Odessa');
insert into cities(fk_country_id, name) values (3, 'Lviv');
insert into cities(fk_country_id, name) values (4, 'Warsaw');
insert into cities(fk_country_id, name) values (4, 'Krakow');
insert into cities(fk_country_id, name) values (5, 'Berlin');
insert into cities(fk_country_id, name) values (5, 'Bremen');
insert into cities(fk_country_id, name) values (5, 'Hanover');
insert into cities(fk_country_id, name) values (5, 'Munich');
insert into cities(fk_country_id, name) values (5, 'Frankfurt am Main');
insert into cities(fk_country_id, name) values (6, 'Dubai');
insert into cities(fk_country_id, name) values (6, 'Abu Dhabi');

--changeset cichan:insertion-into-airports
insert into airports(fk_city_id, name) values (1, 'Minsk National Airport');
insert into airports(fk_city_id, name) values (4, 'Moscow Domodedovo Airport');
insert into airports(fk_city_id, name) values (4, 'Sheremetyevo International Airport');
insert into airports(fk_city_id, name) values (4, 'Vnukovo International Airport');
insert into airports(fk_city_id, name) values (4, 'Zhukovsky International Airport');
insert into airports(fk_city_id, name) values (5, 'Pulkovo Airport');
insert into airports(fk_city_id, name) values (6, 'Koltsovo International Airport');
insert into airports(fk_city_id, name) values (7, 'Kyiv International Airport (Zhuliany)');
insert into airports(fk_city_id, name) values (8, 'Odesa International Airport');
insert into airports(fk_city_id, name) values (9, 'Lviv Danylo Halytskyi International Airport');
insert into airports(fk_city_id, name) values (10, 'Warsaw Chopin Airport');
insert into airports(fk_city_id, name) values (10, 'Warsaw Modlin Airport');
insert into airports(fk_city_id, name) values (11, 'Kraków John Paul II International Airport');
insert into airports(fk_city_id, name) values (12, 'Berlin Brandenburg Airport');
insert into airports(fk_city_id, name) values (13, 'Bremen Airport');
insert into airports(fk_city_id, name) values (14, 'Hannover Airport');
insert into airports(fk_city_id, name) values (15, 'Munich International Airport- Franz Josef Strauß');
insert into airports(fk_city_id, name) values (16, 'Frankfurt Airport');
insert into airports(fk_city_id, name) values (17, 'Dubai International Airport');
insert into airports(fk_city_id, name) values (18, 'Abu Dhabi International Airport');

--changeset cichan:insertion-into-airlines
insert into airlines(name) values ('Belavia');
insert into airlines(name) values ('Aeroflot');
insert into airlines(name) values ('Aurora');
insert into airlines(name) values ('S7 Airlines');
insert into airlines(name) values ('FlyUIA');
insert into airlines(name) values ('LOT');
insert into airlines(name) values ('Eurowings');
insert into airlines(name) values ('Lufthansa');
insert into airlines(name) values ('TUI fly Deutschland');
insert into airlines(name) values ('Air Arabia');
insert into airlines(name) values ('Air Arabia Abu Dhabi');
insert into airlines(name) values ('Emirates');
insert into airlines(name) values ('Etihad Airways');
insert into airlines(name) values ('Flydubai');
insert into airlines(name) values ('Wizz Air Abu Dhabi');
