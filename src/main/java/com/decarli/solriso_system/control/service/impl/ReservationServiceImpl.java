package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.Reservation;
import com.decarli.solriso_system.model.exceptions.RoomReservationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation createReservation(ReservationCreateDto reservation) {
        List<Reservation> reservationsBetween = getReservationsBetween(reservation.getCheckin(), reservation.getCheckout());
        for(Reservation r : reservationsBetween) {
            if(r.getRoom() == reservation.getRoom()) {
                throw new RoomReservationException("This room is already occupied");
            }
        }


        Reservation r = repository.save(ReservationMapper.INSTANCE.toReservation(reservation));
        return r;
    }

    @Override
    public Reservation getReservationById(String id) {
        Reservation reservation = repository.findReservationById(id);
        return reservation;
    }

    @Override
    public List<Reservation> getReservationsToday() {
        List<Reservation> reservations = repository.findReservationByCheckin(LocalDate.now());
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByRoom(int room) {
        List<Reservation> reservations = repository.findReservationByRoom(room);
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByResponsibleName(String name) {
        List<Reservation> reservations = repository.findReservationByResponsibleName(name);
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsBetween(LocalDate checkin, LocalDate checkout) {
        List<Reservation> reservations = repository.findReservationBetween(checkin, checkout);
        return reservations;
    }

    @Override
    public Reservation updateReservation(ReservationUpdateDto update) {
        Reservation reservation = getReservationById(update.getId());

        reservation.setRoom(update.getRoom());
        reservation.setQuantGuests(update.getQuantGuests());
        reservation.setTypeReservation(update.getTypeReservation());
        reservation.setStatus(update.getStatus());
        reservation.setCheckin(update.getCheckin());
        reservation.setCheckout(update.getCheckout());
        reservation.setEntryValue(update.getEntryValue());
        reservation.setTotalValue(update.getTotalValue());
        reservation.setResponsible(update.getResponsible());
        reservation.setParking(update.getParking());

        return reservation;
    }

    @Override
    public void deleteReservation(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = repository.findAll();
        return reservations;
    }
}
