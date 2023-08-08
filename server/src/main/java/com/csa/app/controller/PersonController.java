package com.csa.app.controller;

import com.csa.app.dto.request.PersonDto;
import com.csa.app.dto.request.PersonDtoRequest;
import com.csa.app.dto.request.search.SearchDto;
import com.csa.app.dto.response.BriefPersonDto;
import com.csa.app.dto.response.PersonProfileDto;
import com.csa.app.dto.response.exception.AppErrorDto;
import com.csa.app.service.person.PersonDataService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-dataPerson")
public class PersonController {
    private final PersonDataService personDataService;

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam("id") Long id, @RequestParam("pass") String pass) {
        PersonProfileDto personProfileDto = personDataService.updatePassword(id, pass);
        if (personProfileDto != null) {
            return ResponseEntity.ok(personProfileDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AppErrorDto(HttpStatus.NOT_FOUND.value(), "Person not found"));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateData(@RequestBody PersonDtoRequest dto) {
        return personDataService.updateData(dto);
    }

    @GetMapping("/by-username")
    public ResponseEntity<?> getUserByUsername(@RequestBody @Email(message = "Invalid email format")
                                               @NotEmpty(message = "Email cannot be empty") String email) {
        BriefPersonDto briefPersonDto = personDataService.getUserByUsername(email);
        if (briefPersonDto != null) {
            return ResponseEntity.ok(briefPersonDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AppErrorDto(HttpStatus.BAD_REQUEST.value(), "Пользователь с данным email не найден"));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam("email") @Email(message = "Invalid email format")
                                               @NotEmpty(message = "Email cannot be empty") String email) {
        PersonProfileDto personProfileDto = personDataService.getProfile(email);
        if (personProfileDto != null) {
            return ResponseEntity.ok(personProfileDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AppErrorDto(HttpStatus.BAD_REQUEST.value(), "Пользователь с данным email не найден"));
        }
    }

    @GetMapping("/another-profile")
    public ResponseEntity<?> getAnotherProfile(@RequestParam("personId") Long personId, @RequestParam("anotherId") Long anotherId) {
        return personDataService.getAnotherProfile(personId, anotherId);
    }

    @PostMapping("/search{id}")
    public ResponseEntity<?> searchPerson(@PathVariable("id") Long id, @RequestBody SearchDto dto) {
        return personDataService.searchPerson(id, dto);
    }

}
