DROP DATABASE IF EXISTS webtodolist_db;
CREATE DATABASE webtodolist_db;
USE webtodolist_db;

DROP TABLE IF EXISTS account;
CREATE TABLE account (
	id_account int PRIMARY KEY auto_increment,
	accountname varchar(100) NOT NULL,
	account_password varchar(100) NOT NULL,
	account_role varchar(10) NOT NULL /*intructor or student*/
);

CREATE TABLE todo (
	id_todo int PRIMARY KEY auto_increment,
	todo_description varchar(1000)
);

select * from account;
select * from todo;

INSERT INTO todo VALUES (1,"Maths");
INSERT INTO todo VALUES (2,"French");
INSERT INTO todo VALUES (3,"Exercise 12 page 40 English");

INSERT INTO account VALUES (1,"Jean","1234","student");
INSERT INTO account VALUES (2,"Jeanne","XXXX","instructor");