# --- !Ups

create table user (
  id                        bigint not null,
  code                      varchar(255),
  name                      varchar(255),
  surname                   varchar(255),
  email                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  role                      integer,
  constraint ck_user_role check (role in (0,1,2,3,4,5,6,7)),
  constraint pk_user primary key (id))
;

create sequence user_seq;

insert into user (id, code, name, surname, email, username, password, role) values (1,'123','diego','otero','diego@diego.com','admin','admin', 1);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

