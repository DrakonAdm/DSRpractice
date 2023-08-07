package com.csa.app.entity.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invite")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "from_id", foreignKey = @ForeignKey(name = "fk_invite_from"))
    private Long fromId;

    @JoinColumn(name = "to_id", foreignKey = @ForeignKey(name = "fk_invite_to"))
    private Long toId;


    @Enumerated(EnumType.STRING)
    private InviteStatus status;
}

//    @JoinColumn(name = "from_id", foreignKey = @ForeignKey(name = "fk_invite_from"))
//    private Person from;
//
//    @JoinColumn(name = "to_id", foreignKey = @ForeignKey(name = "fk_invite_to"))
//    private Person to;

