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
  contribution_id           bigint not null,
  name                      varchar(255),
  constraint pk_contributor primary key (id))
;

create sequence contribution_seq;

create sequence contributor_seq;

alter table contributor add constraint fk_contributor_contribution_1 foreign key (contribution_id) references contribution (id) on delete restrict on update restrict;
create index ix_contributor_contribution_1 on contributor (contribution_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contribution;

drop table if exists contributor;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contribution_seq;

drop sequence if exists contributor_seq;

