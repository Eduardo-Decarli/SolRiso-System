package com.decarli.solriso_system.model.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminResponseDto {

    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Email can't be null")
    private String email;
    @NotNull(message = "Role can't be null")
    private String role;
}
