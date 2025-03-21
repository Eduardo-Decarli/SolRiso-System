package com.decarli.solriso_system.model.dto.reservation;

import com.decarli.solriso_system.model.entities.Parking;
import com.decarli.solriso_system.model.entities.ResponsibleBooking;
import com.decarli.solriso_system.model.enums.Status;
import com.decarli.solriso_system.model.enums.TypeReservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationResponseDto {

    private String id;
    @NotNull(message = "Room number can't be null")
    private int room;
    @NotNull(message = "quantity guests can't be null")
    private int quantGuests;
    private TypeReservation typeReservation;
    @NotNull(message = "Status can't be null")
    private Status status;
    @NotNull(message = "Date of Check-in can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Date of check-out can't be null")
    private LocalDate checkout;
    private double entryValue;
    @NotNull(message = "The total value can't be null")
    private double totalValue;

    @NotNull(message = "The responsible of the reservation can't be null")
    @Valid
    private ResponsibleBooking responsible;

    @Valid
    private Parking parking;
}
