package com.csa.app.controller;

import com.csa.app.dto.auth.LoginResponse;
import com.csa.app.dto.request.RegisterPersonDto;
import com.csa.app.dto.auth.AuthRequest;
import com.csa.app.dto.response.PersonProfileDto;
import com.csa.app.dto.response.exception.AppErrorDto;
import com.csa.app.exceptions.PasswordMismatchException;
import com.csa.app.exceptions.UserExistsException;
import com.csa.app.security.AuthService;
import com.csa.app.security.SecurityUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(authService.createAuthToken(authRequest));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppErrorDto(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegisterPersonDto registerPersonDto) {
        try {
            return ResponseEntity.ok(authService.createNewUser(registerPersonDto));
        } catch (PasswordMismatchException e) {
            return new ResponseEntity<>(new AppErrorDto(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(new AppErrorDto(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //log.info("Logout by token {} called", request.getHeader("Authorization"));
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthRequest authRequest) {
        //log.info("Login by user email {} called", authRequest.getUsername());
        return authService.login(authRequest);
    }

    @GetMapping("/me")
    public ResponseEntity<?> findMe(Authentication authentication) {
        var user = (SecurityUser) authentication.getPrincipal();
        try {
            PersonProfileDto personProfileDto = authService.findById(user.getUserId());
            return ResponseEntity.ok(personProfileDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AppErrorDto(HttpStatus.NOT_FOUND.value(), "Пользователь не найден"));
        }
    }
}
