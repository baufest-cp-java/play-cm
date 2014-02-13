# --- Sample dataset

# --- !Ups

insert into user (id,username,password) values (1,'admin','admin');
insert into role (id,code) values (1,'ADMIN');
insert into permission (id,section,action) values (1,'CONTRIBUTION_TYPE','WRITE');
insert into permission (id,section,action) values (2,'CONTRIBUTION','WRITE');
insert into permission (id,section,action) values (3,'CONTRIBUTOR','WRITE');
insert into permission (id,section,action) values (4,'INDEX','WRITE');
insert into role_permission (role_id,permission_id) values (1,1);
insert into role_permission (role_id,permission_id) values (1,2);
insert into role_permission (role_id,permission_id) values (1,3);
insert into role_permission (role_id,permission_id) values (1,4);
insert into user_role (user_id,role_id) values (1,1);
