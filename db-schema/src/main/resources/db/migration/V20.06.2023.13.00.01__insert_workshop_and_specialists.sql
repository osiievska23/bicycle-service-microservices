insert into service.workshop(id)
values ('bfaaade6-300e-4a6f-b10f-3bb5300cff2f'),
        ('e0a342d5-c926-4dd7-acb4-e6e862c76e54'),
        ('193aa1a1-6d22-42cd-9c83-0150af4cfc87'),
        ('83455eb5-b600-4149-8bc2-a42c1493c68f'),
        ('b9ae27b8-c934-447e-bec7-4e1d2aa46f15');

insert into service.specialist(id, first_name, last_name, dob, workshop_id, busy)
values ('83b7bfdc-0f42-11ee-be56-0242ac120002', 'David', 'Smith', '1974-03-12', 'bfaaade6-300e-4a6f-b10f-3bb5300cff2f', false),
		('3495283d-36f1-4306-a1ce-8275c02a83a9', 'Maria', 'Rodriguez', '1998-11-05', 'bfaaade6-300e-4a6f-b10f-3bb5300cff2f', false),
		('43967139-f10d-41a4-991c-38814ad8ab65', 'James', 'Johnson', '2004-09-03', 'e0a342d5-c926-4dd7-acb4-e6e862c76e54', false),
		('9304a63a-45aa-40e7-8c8c-b44f8fdbd423', 'William', 'Wilson', '2001-09-12', 'e0a342d5-c926-4dd7-acb4-e6e862c76e54', false),
		('fd2a1dec-a589-4964-a3b4-5ed8d1b1d73a', 'Charles', 'Smith', '2001-07-10', 'e0a342d5-c926-4dd7-acb4-e6e862c76e54', false),
		('ae7c7ef2-47d9-4e1e-9e80-d5cdc4c5d044', 'Joseph', 'Jones', '1999-04-12', '193aa1a1-6d22-42cd-9c83-0150af4cfc87', false),
		('839c379a-6793-47fc-b415-85757bc6d37e', 'Samuel', 'Brown', '1999-08-10', '83455eb5-b600-4149-8bc2-a42c1493c68f', false),
		('6d833530-5884-45d5-a921-217512e00b3d', 'Thomas', 'Clark', '19997-12-04', '83455eb5-b600-4149-8bc2-a42c1493c68f', false),
		('90221a7f-d78a-4a52-8bc0-9b08fdf998be', 'Henry', 'Jones', '19987-10-06', '83455eb5-b600-4149-8bc2-a42c1493c68f', false),
		('4ff6a067-a5e7-46d7-9c6a-bf8ea0933b07', 'Nancy', 'David', '1992-11-11', '83455eb5-b600-4149-8bc2-a42c1493c68f', false),
		('d77deaf8-75e6-4693-b100-3f779e79d8f8', 'Margaret', 'Taylor', '1995-08-10', 'b9ae27b8-c934-447e-bec7-4e1d2aa46f15', false);

insert into service.address(id, city, street, house_number, district, zip_code)
values ('82bb381c-a361-4351-a5f8-c6ac38d0c101', 'Mankato', 'Nulla St.', '711-2880', 'Mississippi', '96522'),
		('632a6cd1-41f4-4464-9e72-1b782e24d3fd', 'Frederick', 'Fusce Rd.', '283 8562', 'Nebraska', '20620'),
		('435afc7e-e032-48bb-8053-56b02e8aa5c4', 'Azusa', 'Sit Rd.', '867-859', 'New York', '39531');

insert into service.client(id, first_name, last_name, dob, address_id)
values ('cfa721a6-d26e-4941-9dc4-91cb12c3239b', 'Ramon', 'Miles', '1999-05-14', '82bb381c-a361-4351-a5f8-c6ac38d0c101'),
		('0de00f6e-40e7-423a-abcd-aab797937932', 'Lindsey', 'Stirling', '1986-09-21', '632a6cd1-41f4-4464-9e72-1b782e24d3fd'),
		('98ea0e86-e7b6-4056-9397-9469a09210df', 'Scarlett', 'Johansson', '1984-11-22', '435afc7e-e032-48bb-8053-56b02e8aa5c4');

