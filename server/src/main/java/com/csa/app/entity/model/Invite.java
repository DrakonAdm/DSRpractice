package com.csa.app.entity.model;

import javax.persistence.*;

@Entity
class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Person from;

    @Column
    private Person to;

    @Enumerated(EnumType.ORDINAL)
    private InviteStatus status;
}
