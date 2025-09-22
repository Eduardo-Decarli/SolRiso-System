package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.request.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.request.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationService {

    @Transactional
    Reservation createReservation(ReservationCreateDto create);
    Reservation getReservationById(UUID id);
    List<Reservation> getReservationsToday();
    List<Reservation> getReservationsByRoom(int room);
    List<Reservation> getReservationsByResponsibleName(String name);
    List<Reservation> getReservationsBetween(LocalDate checkin, LocalDate checkout);
    @Transactional
    Reservation updateReservation(ReservationUpdateDto update);
    List<Reservation> getAllReservations();
    void deleteReservation(UUID id);
}
