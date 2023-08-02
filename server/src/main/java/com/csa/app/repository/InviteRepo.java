package com.csa.app.repository;

import com.csa.app.entity.model.Invite;
import com.csa.app.entity.model.InviteStatus;
import com.csa.app.entity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteRepo extends JpaRepository<Invite, Long> {
    List<Invite> findByToIdAndStatus(Long toId, InviteStatus status);
}
