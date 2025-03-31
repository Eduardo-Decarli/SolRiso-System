package com.decarli.solriso_system.model.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Dados de retorno na busca por um usuário")
public class AdminResponseDto {

    @Schema(description = "Nome Completo do Usuário", example = "Rodrigo da Silva")
    @NotNull(message = "Name can't be null")
    private String name;

    @Schema(description = "Email do Usuário sendo um Identificador Único", example = "example@gmail.com")
    @NotNull(message = "Email can't be null")
    private String email;

    @Schema(description = "Cargo do usuário no sistema", example = "ADMIN")
    @NotBlank(message = "Role can't be null")
    @Pattern(regexp = "ADMIN|USER")
    private String role;
}
