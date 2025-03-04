package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.data.mongodb.repository.Query;

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
