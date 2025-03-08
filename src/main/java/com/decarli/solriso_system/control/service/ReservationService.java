package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponseDto createReservation(ReservationCreateDto reservation);
    Reservation getReservationById(String id);
    List<ReservationResponseDto> getReservationsToday();
    List<ReservationResponseDto> getReservationsByRoom(int roomNumber);
    List<ReservationResponseDto> getReservationsByResponsibleName(String name);
    List<ReservationResponseDto> getReservationsBetween(LocalDate checkin, LocalDate checkout);
    ReservationResponseDto updateReservation(ReservationUpdateDto reservation);
    void deleteReservation(String id);
}
