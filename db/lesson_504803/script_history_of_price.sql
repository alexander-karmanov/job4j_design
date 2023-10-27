/* 3 */

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function ins_data()
returns trigger as
$$
    BEGIN
        update history_of_price
        set history_of_price.name = products.name,
        history_of_price.price = products.price,
        history_of_price.date = current_date;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger insert_data_trigger
    after insert
    on products 
    for each row
    execute procedure ins_data();
