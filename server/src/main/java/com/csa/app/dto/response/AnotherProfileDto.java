package com.csa.app.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class AnotherProfileDto {
    private long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    @NotBlank(message = "Data birthday cannot be blank")
    private LocalDate date;

    private String phone;
    private String city;
    private String country;
    private String description;

    private boolean isFriend;

    public AnotherProfileDto(long id, String name, String surname, LocalDate date, String phone, String city,
                             String country, String description, boolean isFriend) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.description = description;
        this.isFriend = isFriend;
    }

}
