package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.impl.AdminServiceImp;
import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.admin.AdminLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AdminController {

    private final AdminServiceImp adminServiceImp;

    public AdminController(AdminServiceImp adminServiceImp) {
        this.adminServiceImp = adminServiceImp;
    }

    @Operation(
            summary = "Admin Login",
            description = "Realiza o login de um administrador com email e senha.",
            tags = {"Auth"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "token-xyz"))),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Email ou senha incorretos"))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@Parameter(description = "Objeto contendo as credenciais de login", required = true) @RequestBody @Valid AdminLoginDto adminLoginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminServiceImp.login(adminLoginDto.getEmail(), adminLoginDto.getPassword()));
    }

    @Operation(
            summary = "Admin Register",
            description = "Realiza o registro de um novo administrador com informações básicas.",
            tags = {"Auth"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administração registrada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Admin criado com sucesso"))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Email já registrado"))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Parameter(description = "Objeto contendo as informações para registrar um novo administrador", required = true) @RequestBody @Valid AdminCreateDto createDto) {
        adminServiceImp.register(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
