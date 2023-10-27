create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

/* 1 */
create or replace function taxes_plus()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger taxes_plus_trigger
    after insert
    on products
    for each statement
    execute procedure taxes_plus();

/* 2 */
create or replace function taxes_plus()
    returns trigger as
$$
    BEGIN
        new.price = price + price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_trigger
    before insert
    on products
    for each row
    execute procedure taxes_plus();