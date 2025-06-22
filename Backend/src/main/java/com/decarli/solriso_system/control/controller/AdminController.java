package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.AdminService;
import com.decarli.solriso_system.control.service.impl.AdminServiceImp;
import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.admin.AdminLoginDto;
import com.decarli.solriso_system.model.dto.admin.AdminResponseDto;
import com.decarli.solriso_system.model.dto.mapper.AdminMapper;
import com.decarli.solriso_system.model.entities.Admin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AdminLoginDto adminLoginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.login(adminLoginDto.getEmail(), adminLoginDto.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AdminCreateDto createDto) {
        adminService.register(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid AdminLoginDto dto) {
        adminService.forgotPassword(dto.getEmail(), dto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.status(HttpStatus.FOUND).body(adminMapper.toDtoList(admins));
    }

    @GetMapping("/admins/byEmail")
    public ResponseEntity<AdminResponseDto> getAllAdmins(@RequestParam String email) {
        Admin admin = adminService.getAdminByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(adminMapper.toResponseDto(admin));
    }
}
