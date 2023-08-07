package com.csa.app.dto.response.friend;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InviteFriendDto {
    private long id;
    private long personId;

    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Surname cannot be null")
    private String surname;
    @NotBlank(message = "Data birthday cannot be null")
    private LocalDate date;

    public InviteFriendDto(long id, long personId, String name, String surname, LocalDate date) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.date = date;
    }
}

