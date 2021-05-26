----------------------------------------------------------------------
drop table role_table;
drop table user_table;

create table role_table(name varchar(50), id SERIAL NOT NULL, PRIMARY KEY(ID));
create table user_table(login varchar(50), password varchar(500), role_id integer, id serial not null, primary key(id));

alter table user_table add foreign key (role_id) references role_table(id);

insert into role_table (NAME) VALUES ('ROLE_ADMIN');
insert into role_table (NAME) VALUES ('ROLE_USER');
insert into role_table (NAME) VALUES ('ROLE_SECRETARY');
-------------------------------------------------------------------------

ALTER TABLE  user_table ALTER COLUMN password varchar(500) ;
--ALTER TABLE user_table ALTER COLUMN login RENAME TO username;


UPDATE USER_TABLE SET ROLE_ID = 1 WHERE ID = 1;

delete from user_table where id=97;


select * from role_table;
select * from user_table;





