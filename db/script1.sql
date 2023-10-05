create table cars (
	id serial primary key,
	model varchar(255),
	transmission text
	speed int
);

insert into cars (model, transmission, speed) 
values ('Kia Rio', 'automatic', 150);

update cars set model = 'Volkswagen Polo';

delete from cars;

select * from cars;