drop database if exists eshop;
create database eshop;
use eshop;

drop table if exists t_account;
drop table if exists t_group;
drop table if exists t_role;
drop table if exists t_permission;

drop table if exists t_account_role;
drop table if exists t_account_group;
drop table if exists t_group_role;
drop table if exists t_role_permission;
drop table if exists t_account_token;


create table t_account (
  id bigint auto_increment,
  accountId varchar(100),
  name varchar(100),
  password varchar(100),
  salt varchar(100),
  email varchar(100),
  type int,
  enable tinyint,
  deleted tinyint,
  locked tinyint,
  constraint pk_t_account primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_name on t_account(name);

create table t_group (
  id bigint auto_increment,
  name varchar(100),
  type int,
  constraint pk_t_group primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_name on t_group(name);

create table t_role(
  id bigint auto_increment,
  name varchar(100),
  type int,
  constraint pk_t_role primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_name on t_role(name);

create table t_permission(
  id bigint auto_increment,
  name varchar(100),
  type int,
  constraint pk_t_permission primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_name on t_permission(name);

create table t_account_role(
  id bigint auto_increment,
  account_id bigint,
  role_id bigint,
  constraint pk_t_account_role primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table t_account_group(
	id bigint auto_increment,
	account_id bigint,
	group_id bigint,
	constraint pk_t_account_group primary key(id)
)charset=utf8 ENGINE=InnoDB;

create table t_group_role(
	id bigint auto_increment,
	group_id bigint,
	role_id bigint,
	constraint pk_t_group_role primary key(id)
)charset=utf8 ENGINE=InnoDB;

create table t_role_permission(
	id bigint auto_increment,
	role_id bigint,
	permission_id bigint,
	constraint pk_t_role_permission primary key(id)
)charset=utf8 ENGINE=InnoDB;

create table t_account_token(
	id bigint auto_increment,
	accountId varchar(100),
	token varchar(100),
	constraint pk_t_client primary key(id)
)charset=utf8 ENGINE=InnoDB;

insert into t_account(name,password)values('zhang','123');


