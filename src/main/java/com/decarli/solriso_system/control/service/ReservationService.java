package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.ReservationResponseDto;
import com.decarli.solriso_system.model.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponseDto createReservation(Reservation reservation);
    ReservationResponseDto getReservationByResponsibleName(String name);
    List<ReservationResponseDto> getReservationsToday();
    List<ReservationResponseDto> getReservationsByRoom(int roomNumber);
    List<ReservationResponseDto> getReservationsBetween(LocalDate checkin, LocalDate checkout);
    ReservationResponseDto updateReservation(Reservation reservation);
    void deleteReservation(Reservation reservation);
}
