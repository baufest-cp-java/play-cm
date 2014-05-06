# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contribution (
  id                        bigint not null,
  title                     varchar(255),
  contribution_type_id      bigint,
  description               varchar(255),
  constraint pk_contribution primary key (id))
;

create table contribution_type (
  id                        bigint not null,
  code                      varchar(255),
  name                      varchar(255),
  score                     bigint,
  constraint pk_contribution_type primary key (id))
;

create table contributor (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  role                      integer,
  constraint ck_contributor_role check (role in (0,1,2,3,4,5,6,7)),
  constraint pk_contributor primary key (id))
;


create table contributor_contribution (
  contributor_id                 bigint not null,
  contribution_id                bigint not null,
  constraint pk_contributor_contribution primary key (contributor_id, contribution_id))
;
create sequence contribution_seq;

create sequence contribution_type_seq;

create sequence contributor_seq;

alter table contribution add constraint fk_contribution_contributionTy_1 foreign key (contribution_type_id) references contribution_type (id) on delete restrict on update restrict;
create index ix_contribution_contributionTy_1 on contribution (contribution_type_id);



alter table contributor_contribution add constraint fk_contributor_contribution_c_01 foreign key (contributor_id) references contributor (id) on delete restrict on update restrict;

alter table contributor_contribution add constraint fk_contributor_contribution_c_02 foreign key (contribution_id) references contribution (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contribution;

drop table if exists contributor_contribution;

drop table if exists contribution_type;

drop table if exists contributor;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contribution_seq;

drop sequence if exists contribution_type_seq;

drop sequence if exists contributor_seq;

