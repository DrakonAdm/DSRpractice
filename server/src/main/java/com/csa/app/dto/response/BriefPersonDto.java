package com.csa.app.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BriefPersonDto {
    private long id;

    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Surname cannot be null")
    private String surname;
    @NotBlank(message = "Data birthday cannot be null")
    private LocalDate date;

    public BriefPersonDto(long id, String name, String surname, LocalDate date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date = date;
    }
}
