package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.impl.AuthService;
import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.admin.AdminLoginDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AdminController {

    private final AuthService authService;

    public AdminController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AdminLoginDto adminLoginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(adminLoginDto.getEmail(), adminLoginDto.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AdminCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(createDto));
    }
}
