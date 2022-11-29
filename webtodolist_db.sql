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

select * from todo;

INSERT INTO todo VALUES (1,"abc");
INSERT INTO todo VALUES (2,"Je suis");
INSERT INTO todo VALUES (3,"lalala");