select *
from travel1.place;

alter table travel1.place drop column detail_Pic  ;
alter table travel1.place add column detail_Pic varchar(255) default '' after `desc` ;

desc travel1.place;






drop table if exists travel1.user;

create table travel1.user (
  id int primary key auto_increment comment 'id PK ',
  name varchar(255) comment 'name NN',
  password varchar(255)  comment 'password NN',
  mail varchar(255) unique comment 'mail NN'
) default charset=utf8;

# insert into travel1.user (id, name, password, mail)
# values (null ,"王冠华","123456","wgh0807@qq.com");

truncate travel1.user;


select *
from travel1.user;


drop table if exists travel1.manager;
create table travel1.manager (
  id       int primary key auto_increment
  comment 'id PK',
  name     varchar(255) not null
  comment 'name NN',
  password varchar(255) not null
  comment 'password NN',
  parentId int
);

alter table travel1.manager
  add constraint manager_fk_parentId
foreign key (parentId) references travel1.manager (id);

insert into travel1.manager (id, name, password)
values (0, '', '');

truncate travel1.manager;

select * from travel1.manager;


truncate travel1.user;
select *
from travel1.user;