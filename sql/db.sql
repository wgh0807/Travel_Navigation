drop table if exists travel1.user;

create table travel1.user(
  id int primary key auto_increment comment 'id PK ',
  name varchar(255) comment 'name NN',
  password varchar(255)  comment 'password NN',
  mail varchar(255) unique comment 'mail NN'
);

select *
from travel1.user;