drop database if exists account_db;
create database account_db;
use account_db;

drop table if exists account;
drop table if exists role;
drop table if exists permission;

create table account (
  id bigint auto_increment,
  username varchar(100),
  password varchar(100),
  password_salt varchar(100),
  constraint pk_account primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_username on account(username);

create table role(
  id bigint auto_increment,
  username varchar(100),
  rolename varchar(100),
  constraint pk_role primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_username_rolename on role(username, rolename);

create table permission(
  id bigint auto_increment,
  rolename varchar(100),
  permission varchar(100),
  constraint pk_permission primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_rolename_permission on permission(rolename, permission);

insert into account(username,password)values('zhang','123');


