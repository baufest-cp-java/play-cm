# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contribution (
  id                        bigint not null,
  contribution_type_id      bigint not null,
  description               varchar(255) not null,
  evidence                  varchar(255) not null,
  constraint pk_contribution primary key (id))
;

create table contribution_type (
  id                        bigint not null,
  code                      varchar(10) not null,
  name                      varchar(100) not null,
  short_name                varchar(20) not null,
  area                      integer(50) not null,
  score                     integer not null,
  unit                      integer(50) not null,
  period                    integer(50) not null,
  description               varchar(255) not null,
  sequence                  varchar(5) not null,
  motivation                varchar(255) not null,
  constraint ck_contribution_type_area check (area in (0,1)),
  constraint ck_contribution_type_unit check (unit in (0,1)),
  constraint ck_contribution_type_period check (period in (0,1)),
  constraint uq_contribution_type_code unique (code),
  constraint pk_contribution_type primary key (id))
;

create table contributor (
  id                        bigint not null,
  code                      varchar(10) not null,
  name                      varchar(100) not null,
  mail                      varchar(150) not null,
  rol                       integer(10) not null,
  unity                     integer(10) not null,
  constraint ck_contributor_rol check (rol in (0,1,2,3,4,5,6,7)),
  constraint ck_contributor_unity check (unity in (0,1)),
  constraint uq_contributor_code unique (code),
  constraint uq_contributor_mail unique (mail),
  constraint pk_contributor primary key (id))
;

create table permission (
  id                        bigint not null,
  section                   varchar(20) not null,
  action                    varchar(10) not null,
  constraint ck_permission_section check (section in ('INDEX','CONTRIBUTOR','CONTRIBUTION_TYPE','CONTRIBUTION')),
  constraint ck_permission_action check (action in ('WRITE')),
  constraint pk_permission primary key (id))
;

create table role (
  id                        bigint not null,
  code                      varchar(20) not null,
  constraint uq_role_code unique (code),
  constraint pk_role primary key (id))
;

create table user (
  id                        bigint not null,
  username                  varchar(50) not null,
  password                  varchar(50) not null,
  constraint uq_user_username unique (username),
  constraint pk_user primary key (id))
;


create table contribution_contributor (
  contribution_id                bigint not null,
  contributor_id                 bigint not null,
  constraint pk_contribution_contributor primary key (contribution_id, contributor_id))
;

create table role_permission (
  role_id                        bigint not null,
  permission_id                  bigint not null,
  constraint pk_role_permission primary key (role_id, permission_id))
;

create table user_role (
  user_id                        bigint not null,
  role_id                        bigint not null,
  constraint pk_user_role primary key (user_id, role_id))
;
create sequence contribution_seq;

create sequence contribution_type_seq;

create sequence contributor_seq;

create sequence permission_seq;

create sequence role_seq;

create sequence user_seq;

alter table contribution add constraint fk_contribution_contributionTy_1 foreign key (contribution_type_id) references contribution_type (id) on delete restrict on update restrict;
create index ix_contribution_contributionTy_1 on contribution (contribution_type_id);



alter table contribution_contributor add constraint fk_contribution_contributor_c_01 foreign key (contribution_id) references contribution (id) on delete restrict on update restrict;

alter table contribution_contributor add constraint fk_contribution_contributor_c_02 foreign key (contributor_id) references contributor (id) on delete restrict on update restrict;

alter table role_permission add constraint fk_role_permission_role_01 foreign key (role_id) references role (id) on delete restrict on update restrict;

alter table role_permission add constraint fk_role_permission_permission_02 foreign key (permission_id) references permission (id) on delete restrict on update restrict;

alter table user_role add constraint fk_user_role_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_role add constraint fk_user_role_role_02 foreign key (role_id) references role (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contribution;

drop table if exists contribution_contributor;

drop table if exists contribution_type;

drop table if exists contributor;

drop table if exists permission;

drop table if exists role;

drop table if exists role_permission;

drop table if exists user;

drop table if exists user_role;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contribution_seq;

drop sequence if exists contribution_type_seq;

drop sequence if exists contributor_seq;

drop sequence if exists permission_seq;

drop sequence if exists role_seq;

drop sequence if exists user_seq;

