package com.decarli.solriso_system.model.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class JwtResponse {

    @NotNull(message = "O token não posse ser um valor vazio")
    private String token;
    @NotNull(message = "O token não pode possuir data indefinida de expiração")
    private Date expiration;

}
