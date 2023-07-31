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

create table public.friends
(
    user_id integer not null
        constraint fk7k6xercebs1wg014xb49hw3h0
            references public.person (id),
    friend_id     integer not null
        constraint fk6k3h7xqyc1d03m13s8apy3efj
            references public.person (id),
    primary key (user_id, friend_id)
);

create table roles (
                       id                    serial,
                       name                  varchar(50) not null,
                       primary key (id)
);

insert into roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN');

CREATE TABLE users_roles (
                             user_id               bigint not null,
                             role_id               int not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references users (id),
                             foreign key (role_id) references roles (id)
);
