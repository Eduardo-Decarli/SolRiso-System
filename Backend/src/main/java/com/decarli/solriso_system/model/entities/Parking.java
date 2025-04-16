package com.decarli.solriso_system.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    private String carType;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;
}
