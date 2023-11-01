CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country)
values ('John', 'Smith', 51, 'UK');

insert into customers(first_name, last_name, age, country)
values ('Ivan', 'Semenov', 41, 'Russia');

insert into customers(first_name, last_name, age, country)
values ('Anna', 'Schmidt', 35, 'Germany');

insert into customers(first_name, last_name, age, country)
values ('Jan', 'Lee', 39, 'China');

insert into customers(first_name, last_name, age, country)
values ('Daria', 'Petrova', 25, 'Russia');

insert into customers(first_name, last_name, age, country)
values ('Alexei', 'Ivanov', 21, 'Russia');


/* список клиентов с минимальным возрастом: */

select * from customers 
where age in (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values (25, 1);
insert into orders(amount, customer_id) values (14, 2);
insert into orders(amount, customer_id) values (16, 3);
insert into orders(amount, customer_id) values (30, 4);
insert into orders(amount, customer_id) values (50, 5);


/* список клиентов, не сделавших ни одного заказа: */

SELECT * FROM customers WHERE customers.id NOT IN (SELECT orders.id FROM orders);

