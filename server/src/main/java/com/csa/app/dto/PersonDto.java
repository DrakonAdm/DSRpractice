package com.csa.app.dto;

import lombok.Data;
import com.csa.app.entity.model.Role;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Data
public class PersonDto {

    private long id;

    @NotEmpty
    @Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$",
            message = "Does not match the format email!")
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