create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments (name) values ('accounting');
insert into departments (name) values ('human resources');
insert into departments (name) values ('sales');
insert into departments (name) values ('IT');
insert into departments (name) values ('legal');

insert into employees(name, department_id) values ('Michael Ford', 1);
insert into employees(name, department_id) values ('John Smith', 3); 
insert into employees(name, department_id) values ('Jack Daniels', 5); 
insert into employees(name, department_id) values ('Richard Benson', 2);
insert into employees(name, department_id) values ('Jessica Nichols', 4);
insert into employees(name, department_id) values ('Emily Dunt', 2);
insert into employees(name, department_id) values ('Amy Scott', null);
insert into employees(name, department_id) values ('Viola Hill', 1);
insert into employees(name, department_id) values ('Mary Duncan', 3);
insert into employees(name, department_id) values ('Katy Pine', null);

select * from departments d left join employees e on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;
select * from departments d full join employees e on e.department_id = d.id;
select * from departments d cross join employees e;

select * from departments d left join employees e on e.department_id = d.id where d.id is null;

select d.id, d.name, e.id, e.name, e.department_id from departments d left join employees e on e.department_id = d.id; 
select d.id, d.name, e.id, e.name, e.department_id from employees e right join departments d on d.id = e.department_id;

create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Nora', 'female');
insert into teens(name, gender) values ('Mia', 'female');
insert into teens(name, gender) values ('Lauren', 'female');
insert into teens(name, gender) values ('Helen', 'female');
insert into teens(name, gender) values ('Kristen', 'female');
insert into teens(name, gender) values ('Nathan', 'male');
insert into teens(name, gender) values ('Joshua', 'male');
insert into teens(name, gender) values ('Arthur', 'male');
insert into teens(name, gender) values ('Mark', 'male');
insert into teens(name, gender) values ('Paul', 'male');

select t1.name, t2.name from teens as t1 cross join teens t2 where t1.gender <> t2.gender;
