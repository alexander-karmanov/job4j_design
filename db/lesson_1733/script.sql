create table car_bodies (
    id serial primary key,
    name varchar(255)
);

create table car_engines (
    id serial primary key,
    name varchar(255)
);

create table car_transmissions (
    id serial primary key,
    name varchar(255)
);

create table cars (
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values ('hatchback');
insert into car_bodies (name) values ('pickup');
insert into car_bodies (name) values ('sedan');
insert into car_bodies (name) values ('station wagon');
insert into car_bodies (name) values ('coupe');
insert into car_bodies (name) values ('SUV');

insert into car_engines (name) values ('1.8 L 20v Turbo');
insert into car_engines (name) values ('1.3 L Alpha SOHC 12V I4');
insert into car_engines (name) values ('2.0 L TSI 170 I4');
insert into car_engines (name) values ('1.5 L U-Line I4 ');
insert into car_engines (name) values ('1.6 L');
insert into car_engines (name) values ('3.5 L I6');
insert into car_engines (name) values ('1357 cc Twin Cam I4');

insert into car_transmissions (name) values ('manual');
insert into car_transmissions (name) values ('automatic');
insert into car_transmissions (name) values ('CVT');

insert into cars (name, body_id, engine_id, transmission_id) values ('Audi A3', 1, 1, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Hyundai Accent', 3, 2, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Volkswagen Tiguan', 6, 3, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Kia Rio', 1, 4, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Lada Granta', 3, 5, 1);
insert into cars (name) values ('Alfa Romeo');

select cars.id, cars.name as car_name, car_bodies.name as body_name,
car_engines.name as engine_name, car_transmissions.name as transmission_name from cars 
full join car_bodies on cars.body_id = car_bodies.id  
full join car_engines on cars.engine_id = car_engines.id
full join car_transmissions on cars.transmission_id = car_transmissions.id

select cars.id, cars.name as car_name, car_bodies.name as body_name
from cars right join car_bodies on cars.body_id = car_bodies.id
where cars.body_id is null

select cars.id, cars.name as car_name, car_engines.name as engine_name
from cars right join car_engines on cars.engine_id = car_engines.id
where cars.engine_id is null

select cars.id, cars.name as car_name, car_transmissions.name as transmission_name
from cars right join car_transmissions on cars.transmission_id = car_transmissions.id
where cars.transmission_id is null

