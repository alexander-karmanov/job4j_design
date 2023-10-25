create table salaries(
    id serial primary key,
    salary int
);

create table titles(
    id serial primary key,
    title varchar(255)
);

create table company(
    id serial primary key,
    name varchar(255),
    address varchar(255)
);

create table departments(
    id serial primary key,
    name varchar(255),
    company_id int references company(id)
);

create table employees(
    id serial primary key,
    birthdate date,
    name varchar(255),
    gender varchar(255),
    salary_id int references salaries(id),
    title_id int references titles(id),
    department_id int references departments(id)
);

insert into salaries (salary) values (40000);
insert into salaries (salary) values (45000);
insert into salaries (salary) values (55000);
insert into salaries (salary) values (65000);
insert into salaries (salary) values (50000);
insert into salaries (salary) values (60000);

insert into titles (title) values ('специалист');
insert into titles (title) values ('ведущий специалист');
insert into titles (title) values ('главный специалист');
insert into titles (title) values ('оператор');
insert into titles (title) values ('старший оператор');
insert into titles (title) values ('кассир');

insert into company (name, address) values ('ООО Торговый дом', 'Москва, ул. Московская, д. 100, ст.50');

insert into departments (name, company_id ) values ('бухгалтерия', 1);
insert into departments (name, company_id ) values ('юридический', 1);
insert into departments (name, company_id ) values ('управление персоналом', 1);
insert into departments (name, company_id ) values ('аппарат гендиректора', 1);
insert into departments (name, company_id ) values ('информационные технологии', 1);
insert into departments (name, company_id ) values ('отдел продаж', 1);

insert into employees (birthdate, name, gender, salary_id, title_id, department_id)
values ('1971-03-05', 'Иван', 'мужской', 1, 1, 1);

insert into employees (birthdate, name, gender, salary_id, title_id, department_id)
values ('1975-11-01', 'Анастасия', 'женский', 2, 2, 2);

insert into employees (birthdate, name, gender, salary_id, title_id, department_id)
values ('1979-05-12', 'Семён', 'мужской', 3, 3, 3);

insert into employees (birthdate, name, gender, salary_id, title_id, department_id)
values ('1981-07-29', 'Ирина', 'женский', 4, 4, 4);

insert into employees (birthdate, name, gender, salary_id, title_id, department_id)
values ('1981-10-10', 'Матвей', 'мужской', 5, 5, 5);

insert into employees (birthdate, name, gender, salary_id, title_id, department_id)
values ('1978-03-15', 'Дарья', 'женский', 6, 6, 6);

select t.title, sum(s.salary) from employees e 
join departments d on e.department_id = d.id
join company c on d.company_id = c.id
join salaries s on e.salary_id = s.id
join titles t on e.title_id = t.id
group by (t.title, s.salary) 
having sum(s.salary) > 50000 and t.title<>'кассир';  

create view show_emps_with_more_50_thous_and_no_cs
as select t.title, sum(s.salary) from employees e 
join departments d on e.department_id = d.id
join company c on d.company_id = c.id
join salaries s on e.salary_id = s.id
join titles t on e.title_id = t.id
group by (t.title, s.salary) 
having sum(s.salary) > 50000 and t.title<>'кассир';  

select * from show_emps_with_more_50_thous_and_no_cs;
