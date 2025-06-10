package com.decarli.solriso_system.model.dto.admin;

import com.decarli.solriso_system.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Criação de um Usuário no Sistema")
public class AdminCreateDto {

    @Schema(description = "Nome Completo do Usuário", example = "Rodrigo da Silva")
    @NotBlank(message = "Name can't be null")
    @Size(min = 5, max = 100, message = "O nome precisa estar entre 2 e 100 caracteres")
    private String name;

    @Schema(description = "Email do Usuário sendo um Identificador Único", example = "example@gmail.com")
    @NotBlank(message = "Email can't be null")
    @Email(message = "Email inválido")
    @Size(max = 255, message = "Email too long")
    private String email;

    @Schema(description = "Senha do Usuário, chave de acesso do usuário", example = "Teste@123")
    @NotNull(message = "Password can't be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "A senha precisa ter no mínimo 8 caracteres, uma letra maiúscula, uma minuscula, um número e um caracter especial")
    private String password;

    @Schema(description = "Cargo do usuário no sistema", example = "ADMIN")
    @NotBlank(message = "Role can't be null")
    @Pattern(regexp = "ADMIN|USER")
    private String role;
}
