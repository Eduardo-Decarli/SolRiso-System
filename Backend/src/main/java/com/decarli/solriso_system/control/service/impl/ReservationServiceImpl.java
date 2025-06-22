package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.AdminRepository;
import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.AdminService;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.mapper.ResponsibleBookingMapper;
import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.Reservation;
import com.decarli.solriso_system.model.exceptions.DateReservationException;
import com.decarli.solriso_system.model.exceptions.EntityNotFoundException;
import com.decarli.solriso_system.model.exceptions.RoomReservationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ResponsibleBookingMapper responsibleBookingMapper;

    @Override
    @Transactional
    public Reservation createReservation(ReservationCreateDto create) {

        logger.info("Creating new reservation {}", create);

        validateReservationDates(create.getCheckin(), create.getCheckout());
        validateRoomViability(create.getRoom(), create.getCheckin(), create.getCheckout());
        Reservation reservation = reservationMapper.toReservation(create);
        reservation.setAdmin(adminService.getAdminByEmail(create.getAdminEmail()));
        log.info("{}",create.getPaid());
        Reservation reservation1 = repository.save(reservation);
        log.info("{}", reservation1);
        return reservation1;
    }

    private void validateReservationDates(LocalDate checkin, LocalDate checkout) {
        if(checkin == null || checkout == null) throw new IllegalArgumentException("Date of checkin or checkout can't be null");

        if(checkin.isAfter(checkout) || checkin.isEqual(checkout)) throw new DateReservationException("Date of check-in would be before checkout");

        // if(checkin.isBefore(LocalDate.now())) throw new DateReservationException("Date of checkin can't be before today");

        logger.info("Validate dates successfully from checkin {} to checkout {} ", checkin, checkout);
    }

    private void validateRoomViability(int room, LocalDate checkin, LocalDate checkout) {
        List<Reservation> reservations = getReservationsBetween(checkin, checkout);

        for(Reservation current : reservations) {
            if(current.getRoom() == room) {
                throw new RoomReservationException("This room is already occupied");
            }
        }

        logger.info("Validate successfully room {} viability from {} to {}", room, checkin, checkout);
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

        logger.info("Found reservation {} by id {}", reservation, id);
        return reservation;
    }

    @Override
    public List<Reservation> getReservationsToday() {
        List<Reservation> reservations = repository.findReservationsToday(LocalDate.now());
        if(reservations.isEmpty()) {
            logger.error("Fail to find reservations today");
            throw new EntityNotFoundException("there are no reservations today");
        }

        logger.info("Found reservations today");
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByRoom(int room) {
        List<Reservation> reservations = repository.findReservationByRoom(room);
        if(reservations.isEmpty()) {
            throw new EntityNotFoundException("there are no reservations for this room");
        }
        logger.info("Found reservations by room {}", room);
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByResponsibleName(String name) {
        List<Reservation> reservations = repository.findReservationByResponsibleName(name);
        if(reservations.isEmpty()) {
            throw new EntityNotFoundException("there are no reservations for this responsible");
        }

        logger.info("Found reservations by responsible name {}", name);
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsBetween(LocalDate checkin, LocalDate checkout) {
        logger.info("Looking for reservations from checkin {} to checkout {}", checkin, checkout);
        return repository.findReservationsBetween(checkin, checkout);
    }


    @Override
    @Transactional
    public Reservation updateReservation(ReservationUpdateDto update) {
        Reservation reservation = getReservationById(update.getId());

        logger.info("Making update about reservation {}", reservation);

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
        reservation.setAdmin(adminService.getAdminByEmail(update.getAdminEmail()));
        reservation.setResponsible(responsibleBookingMapper.toResponsibleBooking(update.getResponsible()));
        reservation.setParking(update.getParking());

        logger.info("Finish update reservation to {}", reservation);

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
        logger.warn("Reservation with id {} deleted", id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = repository.findAll();
        if(reservations.isEmpty()) {
            throw new EntityNotFoundException("there are no reservations in the system");
        }
        logger.info("Got all reservations");
        return reservations;
    }
}
