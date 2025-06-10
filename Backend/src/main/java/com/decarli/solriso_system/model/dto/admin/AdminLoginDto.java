package com.decarli.solriso_system.model.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Dados para login do usuário no sistema")
public class AdminLoginDto {

    @Schema(description = "Email do Usuário sendo um Identificador Único", example = "example@gmail.com")
    @NotNull(message = "Email can't be null")
    private String email;

    @Schema(description = "Senha do Usuário, chave de acesso do usuário", example = "Teste@123")
    @NotNull(message = "A senha não pode ser null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "A senha precisa ter no mínimo 8 caracteres, uma letra maiúscula, uma minuscula, um número e um caracter especial")
    private String password;
}
