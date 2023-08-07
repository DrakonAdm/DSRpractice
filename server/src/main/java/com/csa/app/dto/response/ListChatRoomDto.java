package com.csa.app.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ListChatRoomDto {
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;

    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Surname cannot be null")
    private String surname;
    @NotBlank(message = "Data birthday cannot be null")
    private LocalDate date;
}
