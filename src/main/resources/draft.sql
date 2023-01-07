drop schema if exists piskunou cascade;

set schema 'public';

drop table if exists flight;
drop table if exists "user";

TRUNCATE Table "User";

INSERT INTO "User"(username, authority) VALUES ('Bordan', 'ADMIN');
INSERT INTO "User"(username, authority) VALUES ('Dmitry', 'ADMIN');
INSERT INTO "User"(username, authority) VALUES ('Cichan', 'CLIENT');
INSERT INTO "User"(username, authority) VALUES ('Hanna', 'CLIENT');
INSERT INTO "User"(username, authority) VALUES ('Elizaeta', 'CLIENT');
INSERT INTO "User"(username, authority) VALUES ('Varvara', 'CLIENT');
INSERT INTO "User"(username, authority) VALUES ('Ilya', 'CLIENT');
INSERT INTO "User"(username, authority) VALUES ('Yaroslav', 'CLIENT');

