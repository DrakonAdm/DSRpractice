package com.csa.app.dto.request.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class SearchDto {
    private String name;
    private String surname;
    private LocalDate date;

    private String phone;
    private String city;
    private String country;

    private Integer birthYear;
}

