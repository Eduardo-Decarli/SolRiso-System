package com.decarli.solriso_system.model.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminLoginDto {

    @NotNull(message = "Email can't be null")
    private String email;
    @NotNull(message = "Password can't be null")
    private String password;
}
