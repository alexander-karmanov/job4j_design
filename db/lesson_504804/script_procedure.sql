/* создание таблицы */
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

/* процедура добавления данных */
create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

/* вызов процедуры добавления данных */
call insert_data('молоко', 'производитель 1', 15, 32);
call insert_data('хлеб', 'производитель 2', 20, 30);
call insert_data('колбаса', 'производитель 3', 7, 100);
call insert_data('макароны', 'производитель 4', 10, 50);
call insert_data('масло', 'производитель 5', 17, 70);
call insert_data('мука', 'производитель 6', 0, 70);

/* процедура удаления данных */
create or replace procedure del_data()
language 'plpgsql'
as $$
    BEGIN
    delete from products where products.count = 0;
    END
$$;

/* вызов процедуры удаления данных */
call del_data();

