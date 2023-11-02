create table cars (
    id serial primary key,
    name varchar(25),
    producer varchar(25),
    count integer default 0,
    price integer
);

insert into cars (name, producer, count, price) VALUES ('Audi A3', 'Audi', 5, 8300);
insert into cars (name, producer, count, price) VALUES ('Hyundai Accent', 'Hyundai Motor Company', 20, 1500);
insert into cars (name, producer, count, price) VALUES ('Kia Rio', 'Kia Motors', 17, 11600);
insert into cars (name, producer, count, price) VALUES ('Lada Granta', 'Lada', 17, 7500);

