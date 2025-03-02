package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation getReservationByResponsibleName(String name);
    List<Reservation> getReservationsToday();
    List<Reservation> getReservationsByRoom(int roomNumber);
    List<Reservation> getReservationsBetween(LocalDate checkin, LocalDate checkout);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(Reservation reservation);
}
