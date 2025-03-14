package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.Reservation;
import com.decarli.solriso_system.model.exceptions.DateReservationException;
import com.decarli.solriso_system.model.exceptions.EntityNotFoundException;
import com.decarli.solriso_system.model.exceptions.RoomReservationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation createReservation(ReservationCreateDto create) {
        List<Reservation> reservationsBetween = getReservationsBetween(create.getCheckin(), create.getCheckout());
        for(Reservation r : reservationsBetween) {
            if(r.getRoom() == create.getRoom()) {
                throw new RoomReservationException("This room is already occupied");
            }
        }
        if(create.getCheckin().isAfter(create.getCheckout()) || create.getCheckout().isEqual(create.getCheckin())) {
            throw new DateReservationException("Date of checkin would be before checkout");
        }

        return repository.save(ReservationMapper.INSTANCE.toReservation(create));
    }

    @Override
    public Reservation getReservationById(String id) {
        if(id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be empty");
        }
        Reservation reservation = repository.findReservationById(id);
        if(reservation == null) {
            throw new EntityNotFoundException("Reservation not found");
        }
        return reservation;
    }

    @Override
    public List<Reservation> getReservationsToday() {
        List<Reservation> reservations = repository.findReservationByCheckin(LocalDate.now());
        if(reservations.isEmpty()) {
            throw new EntityNotFoundException("there are no reservations today");
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByRoom(int room) {
        List<Reservation> reservations = repository.findReservationByRoom(room);
        if(reservations.isEmpty()) {
            throw new EntityNotFoundException("there are no reservations for this room");
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByResponsibleName(String name) {
        List<Reservation> reservations = repository.findReservationByResponsibleName(name);
        if(reservations.isEmpty()) {
            throw new EntityNotFoundException("there are no reservations for this responsible");
        }
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

        if(reservation == null) {
            throw new EntityNotFoundException("Reservation not found");
        }

        List<Reservation> reservationsBetween = getReservationsBetween(update.getCheckin(), update.getCheckout());
        for(Reservation r : reservationsBetween) {
            if(r.getRoom() == update.getRoom()) {
                throw new RoomReservationException("This room is already occupied");
            }
        }

        if(update.getCheckin().isAfter(update.getCheckout()) || update.getCheckout().isEqual(update.getCheckin())) {
            throw new DateReservationException("Date of checkin would be before checkout");
        }

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
        if(id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be empty");
        } else if(repository.findReservationById(id) == null) {
            throw new EntityNotFoundException("Reservation not found");
        }
        repository.deleteById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = repository.findAll();
        if(reservations.isEmpty()) {
            throw new EntityNotFoundException("there are no reservations in the system");
        }
        return reservations;
    }
}
