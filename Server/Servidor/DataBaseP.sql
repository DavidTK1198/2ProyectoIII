
CREATE DATABASE DataBaseP;


use DataBaseP;
create table Usuario (
       id  varchar(10)  not null,
       clave varchar(10) not null,
       Primary Key (id)         
     );
insert into Usuario (id,clave) values ('111','111');


