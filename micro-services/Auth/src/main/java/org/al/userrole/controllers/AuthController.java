package org.al.userrole.controllers;

import lombok.RequiredArgsConstructor;
import org.al.userrole.dto.CreateUserRequest;
import org.al.userrole.dto.LoginRequest;
import org.al.userrole.servicesinterface.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        authService.createUserWithRole(
                request.getEmail(),
                request.getPassword(),
                request.getRoleName()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}
