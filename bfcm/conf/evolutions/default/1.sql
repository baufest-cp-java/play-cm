# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contribution (
  id                        bigint not null,
  title                     varchar(255),
  contribution_type         varchar(255),
  constraint pk_contribution primary key (id))
;

create table contributor (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_contributor primary key (id))
;


create table contribution_contributor (
  contribution_id                bigint not null,
  contributor_id                 bigint not null,
  constraint pk_contribution_contributor primary key (contribution_id, contributor_id))
;

create table contributor_contribution (
  contributor_id                 bigint not null,
  contribution_id                bigint not null,
  constraint pk_contributor_contribution primary key (contributor_id, contribution_id))
;
create sequence contribution_seq;

create sequence contributor_seq;




alter table contribution_contributor add constraint fk_contribution_contributor_c_01 foreign key (contribution_id) references contribution (id) on delete restrict on update restrict;

alter table contribution_contributor add constraint fk_contribution_contributor_c_02 foreign key (contributor_id) references contributor (id) on delete restrict on update restrict;

alter table contributor_contribution add constraint fk_contributor_contribution_c_01 foreign key (contributor_id) references contributor (id) on delete restrict on update restrict;

alter table contributor_contribution add constraint fk_contributor_contribution_c_02 foreign key (contribution_id) references contribution (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contribution;

drop table if exists contribution_contributor;

drop table if exists contributor;

drop table if exists contributor_contribution;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contribution_seq;

drop sequence if exists contributor_seq;

