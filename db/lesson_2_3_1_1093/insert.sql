insert into roles (role) values ('admin');
insert into roles (role) values ('user');

insert into users(name, role_id) values ('Jason', 1);
insert into users(name, role_id) values ('Robert', 2);

insert into rules (rule) values ('add item');
insert into rules (rule) values ('edit item');
insert into rules (rule) values ('read item');
insert into rules (rule) values ('delete item');

insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (1, 4);
insert into roles_rules(role_id, rule_id) values (2, 2);
insert into roles_rules(role_id, rule_id) values (2, 3);

insert into categories (category) values ('sports');
insert into categories (category) values ('electronics');
insert into categories (category) values ('books');
insert into categories (category) values ('art');

insert into states(state) values ('confirmed');
insert into states(state) values ('shipped');
insert into states(state) values ('on the way');
insert into states(state) values ('delivered');

insert into items (item, user_id, category_id, state_id) values ('first item', 1, 1, 1);
insert into items (item, user_id, category_id, state_id) values ('second item', 1, 2, 2);
insert into items (item, user_id, category_id, state_id) values ('third item', 2, 3, 3);
insert into items (item, user_id, category_id, state_id) values ('fourth item', 2, 4, 4);

insert into comments (comment, item_id) values ('Decent bike for this price', 4);
insert into comments (comment, item_id) values ('the bike came packaged ok', 5);
insert into comments (comment, item_id) values ('Nice looking bike. Surprisingly light!', 6);
insert into comments (comment, item_id) values ('What is in the picture is not what you get.', 7);

insert into attachs (attach, item_id) values ('file1.jpg', 4);
insert into attachs (attach, item_id) values ('file2.jpg', 5);
