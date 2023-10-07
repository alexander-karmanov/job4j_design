create table country(
    id serial primary key,
    territory int
);

create table capital(
    id serial primary key,
    name varchar(255),
    country_id int references country(id) unique
);

