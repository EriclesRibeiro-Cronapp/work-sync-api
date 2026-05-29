package com.workflow.api.controller;

import com.workflow.api.dto.LoginRequest;
import com.workflow.api.dto.RegisterRequest;
import com.workflow.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {
        service.register(request);

        return ResponseEntity.ok("Usuário cadastrado");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request) {
        String token = service.authenticate(request);

        return ResponseEntity.ok(token);
    }
}
