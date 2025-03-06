package com.decarli.solriso_system.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Parking {
    private String carType;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;
}
