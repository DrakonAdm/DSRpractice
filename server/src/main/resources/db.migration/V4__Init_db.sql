CREATE TABLE invite (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        from_id BIGINT,
                        to_id BIGINT,
                        status varchar(10),
                        CONSTRAINT fk_invite_from FOREIGN KEY (from_id) REFERENCES public.person (id),
                        CONSTRAINT fk_invite_to FOREIGN KEY (to_id) REFERENCES public.person (id)
);
