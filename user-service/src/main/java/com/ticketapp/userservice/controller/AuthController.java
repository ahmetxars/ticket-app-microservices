package com.ticketapp.userservice.controller;

import com.ticketapp.userservice.dto.LoginRequest;
import com.ticketapp.userservice.dto.RegisterRequest;
import com.ticketapp.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest);

        return ResponseEntity.ok(token);




    }
}