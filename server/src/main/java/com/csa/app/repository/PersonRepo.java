package com.csa.app.repository;

import com.csa.app.entity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Boolean existsByUsername(String email);

    Optional<Person> findByUsername(String username);

    Optional<Person> findById(Long id);

    Optional<Person> findAllByCountry(String country);
    Optional<Person> findAllByCity(String city);
    Optional<Person> findAllByDate(LocalDate date);
    Optional<Person> findAllByNameAndSurname(String name, String surname);

}