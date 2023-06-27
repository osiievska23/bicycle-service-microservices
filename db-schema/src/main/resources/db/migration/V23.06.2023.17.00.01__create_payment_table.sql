create table if not exists service.payment (
    id                  uuid            not null    default uuid_generate_v4(),
    client_id           uuid            not null,
    booking_id          uuid            not null,
    price               numeric(10,2)   not null,
    current_status      varchar(45)     not null,
    created_at          timestamp        not null,
    constraint payment_id_pk primary key (id),
    constraint fk_payment_client_id foreign key (client_id) references service.client(id),
    constraint fk_payment_booking_id foreign key (booking_id) references service.booking(id)
);