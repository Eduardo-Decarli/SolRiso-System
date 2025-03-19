package com.decarli.solriso_system.model.dto.admin;

import com.decarli.solriso_system.model.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AdminCreateDto {

    @NotBlank(message = "Name can't be null")
    @Size(min = 5, max = 100, message = "The name must be between 2 and 100 characters long.")
    private String name;

    @NotBlank(message = "Email can't be null")
    @Email(message = "Invalid email")
    @Size(max = 255, message = "Email too long")
    private String email;

    @NotNull(message = "Password can't be null")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "The password has be eight characters, a uppercase, a lowercase, a number and a special character")
    private String password;

    @NotBlank(message = "Role can't be null")
    @Pattern(regexp = "ADMIN|USER")
    private Role role;
}
