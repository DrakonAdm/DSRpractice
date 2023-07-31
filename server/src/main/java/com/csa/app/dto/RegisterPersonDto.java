package com.csa.app.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Data
public class RegisterPersonDto {
    @NotEmpty
    @Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$",
            message = "Does not match the format email!")
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
