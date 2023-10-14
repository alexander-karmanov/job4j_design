create table companies(
    id serial primary key,
    name varchar(255)
);

create table addresses(
    id serial primary key,
    city varchar(255),
	street varchar(255),
	building varchar(255),
	room int,
    company_id int references companies(id)
);

insert into companies(name) values ('First company');
insert into companies(name) values ('Second company');
insert into companies(name) values ('Third company');
insert into companies(name) values ('Fourth company');


insert into addresses(city, street, building, room, company_id)
values ('London', 'Fleet St', '2AB', 50, 1);

insert into addresses(city, street, building, room, company_id)
values ('London', 'Southwark Bridge Rd', '24', 10, 2);

insert into addresses(city, street, building, room, company_id)
values ('New York', 'Park Row', '20a', 15, 2);

insert into addresses(city, street, building, room)
values ('New York', 'Lexington Ave', '300', 332);

insert into addresses(city, street, building, room)
values ('Paris', 'Av. des Champs-Élysées', '121b', 1);

select cmp.name, adr.city, adr.city, adr.street
from addresses as adr join companies as cmp on adr.company_id = cmp.id

select cmp.name as Компания, adr.city as Город, adr.street as Улица, adr.building as Здание, adr.room as Помещение
from addresses as adr join companies as cmp on adr.company_id = cmp.id;

select cmp.name as "Название компании", adr.city || ', ' || adr.street || ', ' || adr.building || ', ' || adr.room as "Адрес компании"
from addresses as adr join companies as cmp on adr.company_id = cmp.id;