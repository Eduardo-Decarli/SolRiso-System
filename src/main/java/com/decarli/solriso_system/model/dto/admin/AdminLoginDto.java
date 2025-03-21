package com.decarli.solriso_system.model.dto.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminLoginDto {

    @NotNull(message = "Email can't be null")
    private String email;
    @NotNull(message = "Password can't be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "The password has be eight characters, a uppercase, a lowercase, a number and a special character")
    private String password;
}
