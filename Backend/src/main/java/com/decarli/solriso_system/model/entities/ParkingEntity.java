package com.decarli.solriso_system.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "PARKING")
public class ParkingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "CHECKIN")
    private LocalDate checkin;
    @Column(name = "CHECKOUT")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;
    @Column(name = "CAR_TYPE")
    private String carType;
}
