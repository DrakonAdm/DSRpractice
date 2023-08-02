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
