
CREATE DATABASE DataBaseP;



create table Usuario (
       id  varchar(10)  not null,
       clave varchar(10) not null,
       estado boolean false,
       Primary Key (id)         
     );
insert into Usuario (id,clave,estado) values ('111','111',false);
use DataBaseP;

