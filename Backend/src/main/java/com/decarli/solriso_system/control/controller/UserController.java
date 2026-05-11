package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.UserService;
import com.decarli.solriso_system.model.dto.request.AdminCreateDto;
import com.decarli.solriso_system.model.dto.request.AdminLoginDto;
import com.decarli.solriso_system.model.dto.response.AdminResponseDto;
import com.decarli.solriso_system.model.dto.mapper.AdminMapper;
import com.decarli.solriso_system.model.dto.response.JwtResponse;
import com.decarli.solriso_system.model.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid AdminLoginDto adminLoginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(adminLoginDto.getEmail(), adminLoginDto.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AdminCreateDto createDto) {
        userService.register(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid AdminLoginDto dto) {
        userService.forgotPassword(dto.getEmail(), dto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins() {
        List<UserEntity> userEntities = userService.getAllAdmins();
        return ResponseEntity.status(HttpStatus.FOUND).body(adminMapper.toDtoList(userEntities));
    }

    @GetMapping("/admins/byEmail")
    public ResponseEntity<AdminResponseDto> getAdminByEmail(@RequestParam String email) {
        UserEntity userEntity = userService.getAdminByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(adminMapper.toResponseDto(userEntity));
    }

    @GetMapping("/token/check")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam String token) {
        JwtResponse jwtResponse = userService.refreshToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }
}
