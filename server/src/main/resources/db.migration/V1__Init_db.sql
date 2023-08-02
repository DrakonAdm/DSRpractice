create table public.person
(
    id       serial
        primary key,
    username    varchar(80) not null unique,,
    name     varchar(60),
    surname  varchar(60),
    password varchar(255),
    phone    varchar(11),
    city varchar(80),
    country varchar(80),
    description varchar(255),
    date date,
);

ALTER TABLE public.person ALTER COLUMN date TYPE date USING to_date(date, 'DD-MM-YYYY');



