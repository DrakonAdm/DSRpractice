package com.csa.app.service;

import com.csa.app.dto.PersonDto;
import com.csa.app.dto.RegisterPersonDto;
import com.csa.app.entity.auth.AuthRequest;
import com.csa.app.entity.auth.AuthResponse;
import com.csa.app.entity.model.Person;
import com.csa.app.exceptions.AppError;
import com.csa.app.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PersonService personService;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegisterPersonDto registerPersonDto) {
        if (!registerPersonDto.getPassword().equals(registerPersonDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (personService.findByUsername(registerPersonDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        Person person = personService.createNewUser(registerPersonDto);
        return ResponseEntity.ok(new PersonDto(person.getId(), person.getUsername(), person.getName(), person.getSurname(), person.getDate()));
    }
}
