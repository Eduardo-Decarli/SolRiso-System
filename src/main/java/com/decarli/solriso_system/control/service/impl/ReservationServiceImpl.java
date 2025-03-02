package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        Reservation r = reservationRepository.save(reservation);
        return r;
    }

    @Override
    public Reservation getReservationByResponsibleName(String name) {
        return null;
    }

    @Override
    public List<Reservation> getReservationsToday() {
        return List.of();
    }

    @Override
    public List<Reservation> getReservationsByRoom(int roomNumber) {
        return List.of();
    }

    @Override
    public List<Reservation> getReservationsBetween(LocalDate checkin, LocalDate checkout) {
        return List.of();
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;
    }

    @Override
    public void deleteReservation(Reservation reservation) {

    }
}
