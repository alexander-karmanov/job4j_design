create table owner(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    model varchar(255),
    owner_id int references owner(id)
);

insert into owner(name) values ('John');

insert into cars(model, owner_id) VALUES ('Toyota', 1);

select * from cars;

select * from owner where id in (select owner_id from cars);