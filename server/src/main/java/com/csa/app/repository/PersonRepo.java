package com.csa.app.repository;

import com.csa.app.entity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> findByUsernameIgnoreCase(String username);
    Optional<Person> findByUsername(String username);


}

//Optional<Person> findById(Long id);