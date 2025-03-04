package com.decarli.solriso_system.model.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Parking {
    private String carType;
    private LocalDate checkin;
    private LocalDate checkout;
}
