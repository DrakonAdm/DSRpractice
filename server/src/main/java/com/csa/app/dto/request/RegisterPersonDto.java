package com.csa.app.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;


@Data
public class RegisterPersonDto {
    @NotEmpty
    @Email(message = "Does not match the format email!")
    private String username;

    private String password;
    private String confirmPassword;

    @NotEmpty(message = "Name cannot be null")
    private String name;

    @NotEmpty(message = "Surname cannot be null")
    private String surname;

    @NotEmpty(message = "Data birthday cannot be null")
    private LocalDate date;
}
