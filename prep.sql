insert into door_controller(door_controller_id, description) values(1, 'enter') on conflict do nothing;
insert into door_controller(door_controller_id, description) values(2, 'exit') on conflict do nothing;

insert into employee(id, card_id, department, name, position) values(1, '1234567', 'IT department', 'admin', 'IT Manager') on conflict do nothing;
insert into employee(id, card_id, department, name, position) values(2, '1234568', 'IT department', 'Константин', 'IT Manager') on conflict do nothing;
insert into employee(id, card_id, department, name, position) values(3, '1234569', 'IT department', 'Сергей', 'IT Manager') on conflict do nothing;

insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(1, '1234567', TIMESTAMP '2023-01-20 08:00:00', 1,1) on conflict do nothing;
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(5, '1234569', TIMESTAMP '2023-01-20 08:59:18', 1,3) on conflict do nothing;
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(3, '1234568', TIMESTAMP '2023-01-20 09:30:31', 1,2) on conflict do nothing;
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(2, '1234567', TIMESTAMP '2023-01-20 17:01:31', 2,1) on conflict do nothing;
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(6, '1234569', TIMESTAMP '2023-01-20 17:24:03', 2,3) on conflict do nothing;
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(4, '1234568', TIMESTAMP '2023-01-20 18:00:20', 2,2) on conflict do nothing;
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(7, '1234567', TIMESTAMP '2023-01-22 08:00:00', 1,1) on conflict do nothing;
insert into pass(id, card_id, date_time, door_controller_id, employee_id) values(8, '1234567', TIMESTAMP '2023-01-22 17:01:31', 2,1) on conflict do nothing;


