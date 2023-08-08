package com.csa.app.specifications;

import com.csa.app.dto.request.search.SearchDto;
import com.csa.app.entity.model.Person;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PersonSpecifications {

    public static Specification<Person> withSearchCriteria(SearchDto dto) {
        return Specification.where(withName(dto.getName()))
                .and(withSurname(dto.getSurname()))
                .and(withDate(dto.getDate()))
                .and(withPhone(dto.getPhone()))
                .and(withCity(dto.getCity()))
                .and(withCountry(dto.getCountry()))
                .and(withBirthYear(dto.getBirthYear()));
    }

    public static Specification<Person> withName(String name) {
        return (root, query, cb) ->{
            if(name == null || name.length() == 0){
                return null;
            }
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Person> withSurname(String surname) {
        return (root, query, cb) ->{
            if(surname == null || surname.length() == 0){
                return null;
            }
            return cb.like(cb.lower(root.get("surname")), "%" + surname.toLowerCase() + "%");
        };
    }

    public static Specification<Person> withDate(LocalDate date) {
        return (root, query, cb) ->{
            if(date == null){
                return null;
            }
            return cb.equal(root.get("date"), date);
        };
    }

    public static Specification<Person> withPhone(String phone) {
        return (root, query, cb) ->{
            if(phone == null || phone.length() == 0){
                return null;
            }
            return cb.like(cb.lower(root.get("phone")), "%" + phone.toLowerCase() + "%");
        };
    }

    public static Specification<Person> withCity(String city) {
        return (root, query, cb) ->{
            if(city == null || city.length() == 0){
                return null;
            }
            return cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    public static Specification<Person> withCountry(String country) {
        return (root, query, cb) ->{
            if(country == null || country.length() == 0){
                return null;
            }
            return cb.like(cb.lower(root.get("country")), "%" + country.toLowerCase() + "%");
        };
    }

    public static Specification<Person> withBirthYear(Integer birthYear) {
        return (root, query, cb) -> {
            if (birthYear == null) {
                return null;
            }
            LocalDate startDate = LocalDate.of(birthYear, 1, 1);
            LocalDate endDate = LocalDate.of(birthYear, 12, 31);
            return cb.between(root.get("date"), startDate, endDate);
        };
    }
}
