package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(ReservationCreateDto reservation);
    Reservation getReservationById(String id);
    List<Reservation> getReservationsToday();
    List<Reservation> getReservationsByRoom(int roomNumber);
    List<Reservation> getReservationsByResponsibleName(String name);
    List<Reservation> getReservationsBetween(LocalDate checkin, LocalDate checkout);
    Reservation updateReservation(ReservationUpdateDto reservation);
    void deleteReservation(String id);
    List<Reservation> getAllReservations();
}
