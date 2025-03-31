package com.decarli.solriso_system.model.dto.reservation;

import com.decarli.solriso_system.model.entities.Parking;
import com.decarli.solriso_system.model.entities.ResponsibleBooking;
import com.decarli.solriso_system.model.enums.Status;
import com.decarli.solriso_system.model.enums.TypeReservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Dados para a atualização de uma reserva existente")
public class ReservationUpdateDto {

    @Schema(description = "ID da reserva a ser atualizada", example = "64f8b9a0e3b2d40a7c8e4b29")
    private String id;

    @Schema(description = "Número do quarto associado à reserva", example = "101")
    @NotBlank(message = "Room number can't be null")
    private Integer room;

    @Schema(description = "Quantidade de hóspedes associados à reserva", example = "2")
    @NotBlank(message = "Quantity guests can't be null")
    private Integer quantGuests;

    @Schema(description = "Tipo da reserva (ex: PHONE, EMAIL, BOOKING, AIRBNB, EXPEDIA)", example = "PHONE")
    @Pattern(regexp = "PHONE|EMAIL|BOOKING|AIRBNB|EXPEDIA")
    private TypeReservation typeReservation;

    @Schema(description = "Status da reserva (ex: ACTIVE, MISSED, CANCELED)", example = "ACTIVE")
    @NotBlank(message = "Status can't be null")
    @Pattern(regexp = "ACTIVE|MISSED|CANCELED")
    private Status status;

    @Schema(description = "Data de check-in no formato dd/MM/yyyy", example = "01/04/2025")
    @NotBlank(message = "Date of Check-in can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;

    @Schema(description = "Data de check-out no formato dd/MM/yyyy", example = "07/04/2025")
    @NotBlank(message = "Date of check-out can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;

    @Schema(description = "Valor de entrada da reserva", example = "200.50")
    private Double entryValue;

    @Schema(description = "Valor total da reserva", example = "1200.75")
    @NotBlank(message = "The total value can't be null")
    private Double totalValue;

    @Schema(description = "Responsável pela reserva")
    @NotBlank(message = "The responsible of the reservation can't be null")
    @Valid
    private ResponsibleBooking responsible;

    @Schema(description = "Informações sobre o estacionamento (se aplicável)")
    @Valid
    private Parking parking;
}
