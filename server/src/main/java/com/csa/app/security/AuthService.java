package com.csa.app.security;

import com.csa.app.dto.auth.LoginResponse;
import com.csa.app.dto.request.PersonDto;
import com.csa.app.dto.request.RegisterPersonDto;
import com.csa.app.dto.auth.AuthRequest;
import com.csa.app.dto.auth.AuthResponse;
import com.csa.app.dto.response.BriefPersonDto;
import com.csa.app.dto.response.PersonProfileDto;
import com.csa.app.entity.model.Person;
import com.csa.app.exceptions.PasswordMismatchException;
import com.csa.app.exceptions.UserExistsException;
import com.csa.app.service.person.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PersonService personService;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponse createAuthToken(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return new AuthResponse(token);
    }

    public PersonDto createNewUser(RegisterPersonDto registerPersonDto) {
        if (!registerPersonDto.getPassword().equals(registerPersonDto.getConfirmPassword())) {
            throw new PasswordMismatchException("Пароли не совпадают");
        }
        if (personService.findByUsername(registerPersonDto.getUsername()).isPresent()) {
            throw new UserExistsException("Пользователь с указанным именем уже существует");
        }
        Person person = personService.createNewUser(registerPersonDto);
        return new PersonDto(person.getId(), person.getUsername(), person.getName(), person.getSurname(), person.getDate());
    }

    public LoginResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()));

        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        Optional<Person> person = personService.getUserByUsername(authRequest.getUsername());
        if (person.isPresent()) {
            PersonProfileDto personProfileDto = new PersonProfileDto(
                    person.get().getId(),
                    person.get().getUsername(),
                    person.get().getName(),
                    person.get().getSurname(),
                    person.get().getDate(),
                    person.get().getPhone(),
                    person.get().getCity(),
                    person.get().getCountry(),
                    person.get().getDescription()
            );

            return new LoginResponse(personProfileDto, token);
        } else {
            return null;
        }
    }

    public PersonProfileDto findById(Long id) throws EntityNotFoundException {
        Person person = personService.findPersonById(id);
        PersonProfileDto personProfileDto = new PersonProfileDto(person.getId(), person.getUsername(), person.getName(), person.getSurname(), person.getDate());
        personProfileDto.setPhone(person.getPhone());
        personProfileDto.setCity(person.getCity());
        personProfileDto.setCountry(person.getCountry());
        personProfileDto.setDescription(person.getDescription());
        return personProfileDto;
    }
}