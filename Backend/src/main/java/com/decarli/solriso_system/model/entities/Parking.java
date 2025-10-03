package com.decarli.solriso_system.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "PARKING")
public class Parking {

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
