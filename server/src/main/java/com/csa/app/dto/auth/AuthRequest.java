package com.csa.app.dto.auth;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AuthRequest {

    @Email(message = "Name cannot be null")
    private String username;
    private String password;
}