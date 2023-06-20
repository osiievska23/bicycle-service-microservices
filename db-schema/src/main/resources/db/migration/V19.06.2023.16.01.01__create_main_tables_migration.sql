create schema if not exists service;

create table if not exists service."user" (
    id              uuid        not null,
    first_name      varchar(45) not null,
    last_name       varchar(45) not null,
    dob             date        not null
);

create table if not exists service.address (
    id              uuid        not null,
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
    id              int      	not null,
    available       boolean     not null default true,
    constraint workshop_id_pk primary key (id)
);

create table if not exists service.specialist (
    workshop_id     int      	not null,
    busy            boolean     not null default false,

	constraint specialist_id_pk primary key (id),
    constraint fk_specialist_workshop foreign key (workshop_id) references service.workshop(id)
) inherits(service."user");

create table if not exists service.repair_service (
    title       varchar(100)    not null,
    price       numeric(10,2)   not null,
    constraint repair_service_title_pk primary key (title)
);

create table if not exists service.booking (
    id              uuid        not null,
    client_id       uuid        not null,
    workshop_id     int         not null,
    specialist_id   uuid        not null,
    address_id      uuid        not null,
    repair_service_title    varchar(100)    not null,
    current_status          varchar(45)     not null,
    failure_messages        text,
    updated_at              timestamp        not null,
    constraint booking_id_pk primary key (id),
    constraint fk_booking_client foreign key (client_id) references service.client(id),
    constraint fk_booking_workshop foreign key (workshop_id) references service.workshop(id),
    constraint fk_booking_specialist foreign key (specialist_id) references service.specialist(id),
    constraint fk_booking_address foreign key (address_id) references service.address(id),
    constraint fk_booking_repair_service foreign key (repair_service_title) references service.repair_service(title)
);