create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    expired_date date,
    price int, 
    type_id int references type(id)
);

insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Хлеб');
insert into type (name) values ('Колбаса');
insert into type (name) values ('Мороженое');

insert into product(name, expired_date, price, type_id)
values ('чеддер', '2023-09-01', 150, 1);

insert into product(name, expired_date, price, type_id)
values ('моцарелла', '2023-09-01', 210, 1);

insert into product(name, expired_date, price, type_id)
values ('гауда', '2023-10-08', 130, 1);

insert into product(name, expired_date, price, type_id)
values ('обезжиренное молоко', '2023-10-12', 90, 2);

insert into product(name, expired_date, price, type_id)
values ('витаминизированное молоко', '2023-10-15', 90, 2);

insert into product(name, expired_date, price, type_id)
values ('пшеничный', '2023-10-03', 60, 3);

insert into product(name, expired_date, price, type_id)
values ('ржаной', '2023-10-04', 60, 3);

insert into product(name, expired_date, price, type_id)
values ('пломбир', '2023-10-09', 75, 5);

insert into product(name, expired_date, price, type_id)
values ('эскимо', '2023-10-12', 81, 5);

insert into product(name, expired_date, price, type_id)
values ('вареная', '2023-10-05', 150, 4);

select pr.name from product as pr inner 
join type as t on type_id = t.id where t.name = 'Сыр';

select pr.name from product as pr 
where pr.name like '%мороженое%';

select pr.name from product as pr 
where pr.expired_date < current_date;

select t.name, max(pr.price) from product as pr inner 
join type as t on type_id = t.id
group by t.name;

select t.name as "Тип продукта", count(pr.name) as Количество from product as pr inner 
join type as t on type_id = t.id
group by t.name;

select pr.name from product as pr inner 
join type as t on type_id = t.id 
where t.name = 'Сыр' OR t.name = 'Молоко';

select t.name as "Тип продукта", count(pr.name) as Количество from product as pr inner 
join type as t on type_id = t.id
group by t.name
having count(pr.name) < 10

select t.name as "Тип подукта", pr.name as "Продукт" from product as pr inner 
join type as t on type_id = t.id
