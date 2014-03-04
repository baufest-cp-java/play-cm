# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contributor (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_contributor primary key (id))
;

create sequence contributor_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contributor;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contributor_seq;

