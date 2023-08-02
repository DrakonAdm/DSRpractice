package com.csa.app.service.person;

import com.csa.app.dto.response.AnotherProfileDto;
import com.csa.app.dto.response.BriefPersonDto;
import com.csa.app.dto.request.PersonDto;
import com.csa.app.dto.request.RegisterPersonDto;
import com.csa.app.dto.response.exception.AppErrorDto;
import com.csa.app.dto.request.search.SearchDto;
import com.csa.app.entity.model.Person;
import com.csa.app.repository.PersonRepo;
import com.csa.app.service.RoleService;
import com.csa.app.specifications.PersonSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepo personRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<Person> findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                person.getPassword(),
                person.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
    public Person createNewUser(RegisterPersonDto registerPersonDto) {
        Person person = new Person();
        person.setUsername(registerPersonDto.getUsername());
        person.setPassword(passwordEncoder.encode(registerPersonDto.getPassword()));
        person.setName(registerPersonDto.getName());
        person.setSurname(registerPersonDto.getSurname());
        person.setDate(registerPersonDto.getDate());
        person.setRoles(List.of(roleService.getUserRole()));
        return personRepository.save(person);
    }

    //Доделать обновление пароля через майл, регистрация с подтверждением почты. + профиль пользователя
    public Person updatePassword(Long id, String pass) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Person oldPerson = person.orElseThrow();
            oldPerson.setPassword(bCryptPasswordEncoder.encode(pass));
            return personRepository.save(oldPerson);
        } else {
            return null;
        }
    }

    public ResponseEntity<?> updateData(PersonDto dto) {
        Optional<Person> optionalPerson = personRepository.findById(dto.getId());
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.orElseThrow();
            if (dto.getName() != null) person.setName(dto.getName());
            if (dto.getSurname() != null) person.setSurname(dto.getSurname());
            if (dto.getDate() != null) person.setDate(dto.getDate());
            if (dto.getPhone() != null) person.setPhone(dto.getPhone());
            if (dto.getCity() != null) person.setCity(dto.getCity());
            if (dto.getCountry() != null) person.setCountry(dto.getCountry());
            if (dto.getDescription() != null) person.setDescription(dto.getDescription());
            personRepository.save(person);
            return ResponseEntity.ok(new PersonDto(person.getId(), person.getUsername(), person.getName(), person.getSurname(), person.getDate()));
        } else {
            return new ResponseEntity<>(new AppErrorDto(HttpStatus.BAD_REQUEST.value(), "Пользователь не существует"), HttpStatus.BAD_REQUEST);
        }
    }

    public Optional<Person> getUserByUsername(String email) {
        return personRepository.findByUsernameIgnoreCase(email.toLowerCase());
    }

    public ResponseEntity<?> getAnotherProfile(Long personId, Long anotherId) {
        var person = personRepository.findById(anotherId);
        if (person.isPresent()) {
            Person anotherPerson = person.orElseThrow();

            boolean isFriend = false;
            Optional<Person> loggedPerson = personRepository.findById(personId);
            if (loggedPerson.isPresent()) {
                isFriend = loggedPerson.get().getFriends().contains(anotherPerson);
            }

            var anotherProfileDto = new AnotherProfileDto(
                    anotherPerson.getId(),
                    anotherPerson.getName(),
                    anotherPerson.getSurname(),
                    anotherPerson.getDate(),
                    anotherPerson.getPhone(),
                    anotherPerson.getCity(),
                    anotherPerson.getCountry(),
                    anotherPerson.getDescription(),
                    isFriend
            );

            return ResponseEntity.ok(anotherProfileDto);
        } else {
            return new ResponseEntity<>(new AppErrorDto(HttpStatus.BAD_REQUEST.value(), "Страница пользователя не найдена"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> searchPerson(SearchDto dto) {
        List<Person> searchResults = personRepository.findAll((Sort) PersonSpecifications.withSearchCriteria(dto));
        if (searchResults.isEmpty()) {
            return ResponseEntity.ok("По вашему запросу пользоваели не найдены");
        }
        return ResponseEntity.ok(searchResults);
    }

    public Person findPersonById(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    public void saveStateChange(Person person){
        personRepository.save(person);
    }

}