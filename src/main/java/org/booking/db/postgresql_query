create table flight
(
    id                   serial       not null
        constraint flight_pk
            primary key,
    airline_name         varchar(255) not null,
    flight_from          varchar(255) not null,
    destination          varchar(255) not null,
    free_places_in_plain integer      not null,
    depart_date_time     timestamp    not null,
    arrive_date_time     timestamp    not null
);

alter table flight
    owner to postgres;

create table "user"
(
    id            serial       not null
        constraint user_pk
            primary key,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    username      varchar(255) not null,
    user_password varchar(255) not null
);

alter table "user"
    owner to postgres;

create table booking
(
    id           serial  not null
        constraint booking_pk
            primary key,
    flight_id    integer not null
        constraint booking_flight_id_fk
            references flight,
    ticket_count integer not null,
    user_id      integer
        constraint booking_user_id_fk
            references "user"
);

alter table booking
    owner to postgres;