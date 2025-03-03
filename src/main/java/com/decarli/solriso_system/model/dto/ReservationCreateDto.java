package com.decarli.solriso_system.model.dto;

import com.decarli.solriso_system.model.entities.Parking;
import com.decarli.solriso_system.model.entities.ResponsibleBooking;
import com.decarli.solriso_system.model.enums.TypeReservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationCreateDto {

    @NotNull(message = "Room number can't be null")
    private int roomNumber;
    @NotNull(message = "quantity guests can't be null")
    private int quantGuests;
    private TypeReservation typeReservation;
    @NotNull(message = "Date of Check-in can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;
    @NotNull(message = "Date of check-out can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;
    private double entryValue;
    @NotNull(message = "The total value can't be null")
    private double totalValue;

    @NotNull(message = "The responsible of the reservation can't be null")
    private ResponsibleBooking responsible;
    private Parking parking;
}
