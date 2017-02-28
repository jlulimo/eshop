drop database if exists eshop;
create database eshop;
use eshop;

drop table if exists t_category;
create table t_category (
  id bigint auto_increment,
  categoryId varchar(100),
  parentId varchar(100),
  name varchar(100),
  cNo varchar(100)
  constraint pk_t_category primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_name on t_category(name);


