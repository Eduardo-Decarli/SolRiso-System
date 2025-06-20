package com.decarli.solriso_system.model.dto.reservation;

import com.decarli.solriso_system.model.dto.responsibleBooking.ResponsibleBookingCreateDto;
import com.decarli.solriso_system.model.entities.Parking;
import com.decarli.solriso_system.model.enums.Payment;
import com.decarli.solriso_system.model.enums.Status;
import com.decarli.solriso_system.model.enums.TypeReservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para a criação de uma reserva no sistema")
public class ReservationCreateDto {

    @Schema(description = "Número do quarto associado à respectiva reserva", example = "101")
    @NotNull(message = "Room number can't be null")
    private int room;

    @Schema(description = "Quantidade de hóspedes associados à reserva", example = "2")
    @NotNull(message = "Quantity guests can't be null")
    private int quantGuests;

    @Schema(description = "Tipo da reserva (ex: PHONE, EMAIL, BOOKING)", example = "PHONE")
    @NotNull(message = "type of reservation can't be null")
    private TypeReservation typeReservation;

    @Schema(description = "Status da reserva (ex: ACTIVE, MISSED, CANCELED)", example = "ACTIVE")
    @NotNull(message = "Status can't be null")
    private Status status;

    @Schema(description = "Data de check-in no formato dd/MM/yyyy", example = "01/04/2025")
    @NotNull(message = "Date of Check-in can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;

    @Schema(description = "Data de check-out no formato dd/MM/yyyy", example = "07/04/2025")
    @NotNull(message = "Date of check-out can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;

    @NotNull(message = "Forma de pagamento inválida")
    private Payment payment;

    @Schema(description = "Valor de entrada da reserva", example = "200.50")
    private double entryValue;

    @Schema(description = "Valor total da reserva", example = "1200.75")
    @NotNull(message = "The total value can't be null")
    private double totalValue;

    @NotNull(message = "Admin email can't be null")
    private String adminEmail;

    @Schema(description = "Responsável pela reserva")
    @NotNull(message = "The responsible of reservation can't be null")
    @Valid
    private ResponsibleBookingCreateDto responsible;

    @Schema(description = "Informações sobre o estacionamento (se aplicável)")
    private Parking parking;
}
