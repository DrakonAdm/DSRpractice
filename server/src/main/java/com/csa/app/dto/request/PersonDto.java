package com.csa.app.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import com.csa.app.entity.model.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Data
@Getter
@Setter
public class PersonDto {

    private long id;

    @NotEmpty
    @Email(message = "Does not match the format email!")
    private String username;

    @NotEmpty(message = "Name cannot be null")
    private String name;
    @NotEmpty(message = "Surname cannot be null")
    private String surname;
    @NotEmpty(message = "Data birthday cannot be null")
    private LocalDate date;

    private String phone;
    private String city;
    private String country;
    private String description;

    private Set<Role> roles;


    public PersonDto(long id, @NonNull String username, @NonNull String name, @NonNull String surname, @NonNull LocalDate date) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.date = date;
    }

}