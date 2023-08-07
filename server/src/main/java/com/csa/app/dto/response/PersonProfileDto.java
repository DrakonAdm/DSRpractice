package com.csa.app.dto.response;

import com.csa.app.entity.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PersonProfileDto {

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

    public PersonProfileDto(long id, @NonNull String username, @NonNull String name, @NonNull String surname, @NonNull LocalDate date) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.date = date;
    }

}
