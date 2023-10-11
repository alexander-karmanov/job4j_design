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

insert into items (item, user_id, category_id, state_id) values ('first item', 1, 1, 1);
insert into items (item, user_id, category_id, state_id) values ('second item', 1, 2, 2);
insert into items (item, user_id, category_id, state_id) values ('third item', 2, 3, 3);
insert into items (item, user_id, category_id, state_id) values ('fourth item', 2, 4, 4);

insert into comments (comment, item_id) values ('Decent bike for this price', 1);
insert into comments (comment, item_id) values ('the bike came packaged ok', 2);
insert into comments (comment, item_id) values ('Nice looking bike. Surprisingly light!', 3);
insert into comments (comment, item_id) values ('What is in the picture is not what you get.', 4);

insert into attachs (attach, item_id) values ('file1.jpg', 1);
insert into attachs (attach, item_id) values ('file2.jpg', 2);

insert into categories (category, item_id) values ('sports', 1);
insert into categories (category, item_id) values ('electronics', 2);
insert into categories (category, item_id) values ('books', 3);
insert into categories (category, item_id) values ('art', 4);

insert into states(state, item_id) values ('confirmed', 1);
insert into states(state, item_id) values ('shipped', 2);
insert into states(state, item_id) values ('on the way', 3);
insert into states(state, item_id) values ('delivered', 4);
