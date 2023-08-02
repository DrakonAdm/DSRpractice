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
