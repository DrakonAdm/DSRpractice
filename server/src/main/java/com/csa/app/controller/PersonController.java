package com.csa.app.controller;

import com.csa.app.dto.request.PersonDto;
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

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable("id") Long id, @RequestParam("pass") String pass) {
        PersonProfileDto personProfileDto = personDataService.updatePassword(id, pass);
        if (personProfileDto != null) {
            return ResponseEntity.ok(personProfileDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AppErrorDto(HttpStatus.NOT_FOUND.value(), "Person not found"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateData(@RequestBody PersonDto dto) {
        return personDataService.updateData(dto);
    }

    @GetMapping("/by-username")
    public ResponseEntity<?> getUserByUsername(@RequestParam("email") @Email(message = "Invalid email format")
                                               @NotEmpty(message = "Email cannot be empty") String email) {
        BriefPersonDto briefPersonDto = personDataService.getUserByUsername(email);
        if (briefPersonDto != null) {
            return ResponseEntity.ok(briefPersonDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AppErrorDto(HttpStatus.BAD_REQUEST.value(), "Пользователь с данным email не найден"));
        }
    }

    @GetMapping("/another-profile")
    public ResponseEntity<?> getAnotherProfile(@RequestParam("personId") Long personId, @RequestParam("anotherId") Long anotherId) {
        return personDataService.getAnotherProfile(personId, anotherId);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchPerson(@RequestBody SearchDto dto) {
        return personDataService.searchPerson(dto);
    }

}
