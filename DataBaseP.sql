
CREATE DATABASE DataBaseP;



create table Usuario (
       id  varchar(10)  not null,
       clave varchar(10) not null,
	   nombre varchar(10) not null,
       Primary Key (id)         
     );
insert into Usuario (id,clave,nombre) values ('111','111','pepito');
use DataBaseP;

