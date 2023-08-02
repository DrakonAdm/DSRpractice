package com.csa.app.service.person;

import com.csa.app.dto.request.PersonDto;
import com.csa.app.dto.request.search.SearchDto;
import com.csa.app.dto.response.BriefPersonDto;
import com.csa.app.dto.response.PersonProfileDto;
import com.csa.app.entity.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PersonDataService {
    private final PersonService personService;

    public PersonProfileDto updatePassword(Long id, String pass) {
        Person person = personService.updatePassword(id, pass);
        if (person != null) {
            PersonProfileDto personProfileDto = new PersonProfileDto(person.getId(), person.getUsername(), person.getName(),
                    person.getSurname(), person.getDate());
            personProfileDto.setPhone(person.getPhone());
            personProfileDto.setCity(person.getCity());
            personProfileDto.setCountry(person.getCountry());
            personProfileDto.setDescription(person.getDescription());
            return personProfileDto;
        } else {
            return null;
        }
    }

    public ResponseEntity<?> updateData(PersonDto dto) {
        return personService.updateData(dto);
    }

    public BriefPersonDto getUserByUsername(String email) {
        var person = personService.getUserByUsername(email);
        return person.map(value -> new BriefPersonDto(value.getId(), value.getName(), value.getSurname(), value.getDate())).orElse(null);
    }

    public ResponseEntity<?> getAnotherProfile(Long personId, Long anotherId) {
        return personService.getAnotherProfile(personId, anotherId);
    }

    public ResponseEntity<?> searchPerson(SearchDto dto) {
        return personService.searchPerson(dto);
    }
}
