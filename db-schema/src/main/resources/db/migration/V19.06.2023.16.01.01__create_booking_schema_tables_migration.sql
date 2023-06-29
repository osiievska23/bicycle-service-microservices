create extension if not exists "uuid-ossp";

create schema if not exists service;

create table if not exists service."user" (
    id              uuid        not null    default uuid_generate_v4(),
    first_name      varchar(45) not null,
    last_name       varchar(45) not null,
    dob             date        not null
);

create table if not exists service.address (
    id              uuid        not null    default uuid_generate_v4(),
    city            varchar(45) not null,
    street          varchar(45) not null,
    house_number    varchar(20) not null,
    district        varchar(45) not null,
    zip_code        varchar(45) not null,
    constraint address_id_pk primary key (id)
);

create table if not exists service.client (
    address_id      uuid        not null,

	constraint user_id_pk primary key (id),
    constraint fk_client_address foreign key (address_id) references service.address(id)
) inherits(service."user");

create table if not exists service.workshop (
    id              uuid    	not null    default uuid_generate_v4(),
    available       boolean     not null    default true,
    constraint workshop_id_pk primary key (id)
);

create table if not exists service.specialist (
    workshop_id     uuid      	not null,
    busy            boolean     not null    default false,

	constraint specialist_id_pk primary key (id),
    constraint fk_specialist_workshop foreign key (workshop_id) references service.workshop(id)
) inherits(service."user");

create table if not exists service.repair_service (
    title       varchar(100)    not null,
    price       numeric(10,2)   not null,
    constraint repair_service_title_pk primary key (title)
);

create table if not exists service.booking (
    id              uuid        not null    default uuid_generate_v4(),
    client_id       uuid        not null,
    workshop_id     uuid,
    specialist_id   uuid,
    address_id      uuid        not null,
    repair_service_title    varchar(100)    not null,
    current_status          varchar(45)     not null,
    failure_messages        text,
    updated_at              timestamp        not null,
    constraint booking_id_pk primary key (id),
    constraint fk_booking_client_id foreign key (client_id) references service.client(id),
    constraint fk_booking_workshop_id foreign key (workshop_id) references service.workshop(id),
    constraint fk_booking_specialist_id foreign key (specialist_id) references service.specialist(id),
    constraint fk_booking_address_id foreign key (address_id) references service.address(id),
    constraint fk_booking_repair_service_title foreign key (repair_service_title) references service.repair_service(title)
);