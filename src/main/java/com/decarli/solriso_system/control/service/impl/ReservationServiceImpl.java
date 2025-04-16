package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.mapper.ResponsibleBookingMapper;
import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.Reservation;
import com.decarli.solriso_system.model.exceptions.DateReservationException;
import com.decarli.solriso_system.model.exceptions.EntityNotFoundException;
import com.decarli.solriso_system.model.exceptions.RoomReservationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final ResponsibleBookingMapper responsibleBookingMapper;

    public ReservationServiceImpl(ReservationRepository repository, ResponsibleBookingMapper responsibleBookingMapper) {
        this.repository = repository;
        this.responsibleBookingMapper = responsibleBookingMapper;
    }

    @Override
    @Transactional
    public Reservation createReservation(ReservationCreateDto create) {

        validateReservationDates(create.getCheckin(), create.getCheckout());
        validateRoomViability(create.getRoom(), create.getCheckin(), create.getCheckout());

        return repository.save(ReservationMapper.INSTANCE.toReservation(create));
    }

    private void validateReservationDates(LocalDate checkin, LocalDate checkout) {
        if(checkin == null || checkout == null) throw new IllegalArgumentException("Date of checkin or checkout can't be null");

        if(checkin.isAfter(checkout) || checkin.isEqual(checkout)) throw new DateReservationException("Date of check-in would be before checkout");

        if(checkin.isBefore(LocalDate.now())) throw new DateReservationException("Date of checkin can't be before today");
    }

    private void validateRoomViability( int room, LocalDate checkin, LocalDate checkout) {
        List<Reservation> reservations = getReservationsBetween(checkin, checkout);
        for(Reservation current : reservations) {
            if(current.getRoom() == room) {
                throw new RoomReservationException("This room is already occupied");
            }
        }
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
    @Transactional
    public Reservation updateReservation(ReservationUpdateDto update) {
        Reservation reservation = getReservationById(update.getId());

        validateReservationDates(update.getCheckin(), update.getCheckout());
        validateRoomViability(update.getRoom(), update.getCheckin(), update.getCheckout());

        reservation.setRoom(update.getRoom());
        reservation.setQuantGuests(update.getQuantGuests());
        reservation.setTypeReservation(update.getTypeReservation());
        reservation.setStatus(update.getStatus());
        reservation.setCheckin(update.getCheckin());
        reservation.setCheckout(update.getCheckout());
        reservation.setEntryValue(update.getEntryValue());
        reservation.setTotalValue(update.getTotalValue());
        reservation.setResponsible(responsibleBookingMapper.toResponsibleBooking(update.getResponsible()));
        reservation.setParking(update.getParking());

        return repository.save(reservation);
    }

    @Override
    @Transactional
    public void deleteReservation(String id) {

        if(id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Id cannot be empty or null");
        }

        if(repository.findReservationById(id) == null) {
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
