package com.csa.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDtoRequest {

    private long id;

    private String name;
    private String surname;
    private LocalDate date;

    private String phone;
    private String city;
    private String country;
    private String description;


    public PersonDtoRequest(long id, @NonNull String name, @NonNull String surname, @NonNull LocalDate date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date = date;
    }

}
