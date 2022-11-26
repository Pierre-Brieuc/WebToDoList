DROP DATABASE IF EXISTS webtodolist_db;
CREATE DATABASE webtodolist_db;
USE webtodolist_db;

DROP TABLE IF EXISTS account;
CREATE TABLE account (
	id_account int PRIMARY KEY,
	accountname varchar(100) NOT NULL,
	account_password varchar(100) NOT NULL,
	account_role varchar(10) NOT NULL /*intructor or student*/
);

CREATE TABLE todo (
	id_todo int PRIMARY KEY,
	todo_description varchar(1000)
);

CREATE TABLE account_todo_connection (
	id_account int,
    id_todo int
);

CREATE TABLE instructor_students_connection (
	id_instructor int, /*account_role = instructor*/
    id_student int     /*account_role = student*/
);

INSERT INTO account VALUES (1,"Joe","XXX","instructor");
INSERT INTO account VALUES (2,"Marie","XXX","instructor");
INSERT INTO account VALUES (3,"Lili","XXX","student");
INSERT INTO account VALUES (4,"Léo","XXX","student");

INSERT INTO todo VALUES (1,"Léo");
INSERT INTO todo VALUES (4,"Joe");
INSERT INTO todo VALUES (2,"Marie");
INSERT INTO todo VALUES (3,"Lili");

select * from todo;