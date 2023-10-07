create table movies(
     id serial primary key,
     title varchar(255)
 );

 create table directors(
     id serial primary key,
     name varchar(255)
 );

  create table directors_movies(
     id serial primary key,
     director_id int references directors(id),
     movie_id int references movies(id)
 );

insert into directors(name) values ('James Cameron');
insert into directors(name) values ('Francis Ford Coppola');
insert into directors(name) values ('Ridley Scott');

insert into movies(title) values ('Titanic');
insert into movies(title) values ('Godfather');
insert into movies(title) values ('Gladiator');

insert into directors_movies(director_id, movie_id) values (1, 1);
insert into directors_movies(director_id, movie_id) values (1, 2);
insert into directors_movies(director_id, movie_id) values (1, 3);
insert into directors_movies(director_id, movie_id) values (2, 1);
insert into directors_movies(director_id, movie_id) values (2, 2);
insert into directors_movies(director_id, movie_id) values (3, 3);