/* создание таблицы */
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

/* функция добавления данных */
create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

/* вызов функции добавления данных */
select f_insert_data('молоко', 'производитель 1', 15, 32);
select f_insert_data('хлеб', 'производитель 2', 20, 30);
select f_insert_data('колбаса', 'производитель 3', 7, 100);
select f_insert_data('макароны', 'производитель 4', 10, 50);
select f_insert_data('масло', 'производитель 5', 17, 70);
select f_insert_data('мука', 'производитель 6', 0, 70);

/* функция удаления данных */
create or replace function del_data_function()
returns void
language 'plpgsql'
as
$$
    begin
        delete from products where products.count = 0;
    end;
$$;

/* вызов функции удаления данных */
select del_data_function();