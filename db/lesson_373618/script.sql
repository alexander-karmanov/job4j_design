create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Samsung Galaxy S22', 54333.99), ('Nokia G100', 5358.84), ('Google Pixel 7 Pro', 45270.00), ('Motorola Moto G 5G', 15923.33);

insert into people(name) values ('John Smith'), ('Michael Douglas'), ('Bruce Willis'), ('Natalie Portman'), ('Jennifer Aniston');

insert into devices_people(device_id, people_id) values (1, 2), (1, 3), (1, 4), (2, 4), (3, 1), (4, 3), (4, 5);

select p.name, avg(price) from devices_people as dev_p
join devices as d on dev_p.device_id = d.id
join people as p on dev_p.people_id = p.id
group by p.name;

select p.name, avg(price) from devices_people as dev_p
join devices as d on dev_p.device_id = d.id
join people as p on dev_p.people_id = p.id
group by p.name 
having avg(price) > 5000;
