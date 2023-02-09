insert into door_controller(door_controller_id, description) values(1, 'enter');
insert into door_controller(door_controller_id, description) values(2, 'exit');

insert into _user(id, email, password, role) values(1, 'admin@mail.ru', '12345', '_ADMIN');
insert into _user(id, email, password, role) values(2, 'controller@mail.ru', '12345', '_CONTROLLER');
insert into _user(id, email, password, role) values(3, 'user@mail.ru', '12345', '_USER');


insert into employee(id, card_id, department, name, position) values(1, '1234567', 'IT department', 'admin', 'IT Manager');
insert into employee(id, card_id, department, name, position) values(2, '1234568', 'IT department', 'Константин', 'IT Manager');
insert into employee(id, card_id, department, name, position) values(3, '1234569', 'IT department', 'Сергей', 'IT Manager');

insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(1, '1234567', TIMESTAMP '2023-01-20 08:00:00', 1,1);

insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(5, '1234569', TIMESTAMP '2023-01-20 08:59:18', 1,3);
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(3, '1234568', TIMESTAMP '2023-01-20 09:30:31', 1,2);
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(2, '1234567', TIMESTAMP '2023-01-20 17:01:31', 2,1);
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(6, '1234569', TIMESTAMP '2023-01-20 17:24:03', 2,3);
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(4, '1234568', TIMESTAMP '2023-01-20 18:00:20', 2,2);
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(7, '1234567', TIMESTAMP '2023-01-22 08:00:00', 1,1);
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(8, '1234567', TIMESTAMP '2023-01-22 17:01:31', 2,1);


