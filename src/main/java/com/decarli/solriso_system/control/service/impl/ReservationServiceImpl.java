package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.Parking;
import com.decarli.solriso_system.model.entities.Reservation;
import com.decarli.solriso_system.model.entities.ResponsibleBooking;
import com.decarli.solriso_system.model.enums.TypeReservation;
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
    public ReservationResponseDto createReservation(ReservationCreateDto reservation) {
        Reservation r = repository.save(ReservationMapper.INSTANCE.toReservation(reservation));
        return ReservationMapper.INSTANCE.toDto(r);
    }

    @Override
    public Reservation getReservationById(String id) {
        Reservation reservation = repository.findReservationById(id);
        return reservation;
    }

    @Override
    public List<ReservationResponseDto> getReservationsToday() {
        List<Reservation> reservations = repository.findReservationByCheckin(LocalDate.now());
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public List<ReservationResponseDto> getReservationsByRoom(int room) {
        List<Reservation> reservations = repository.findReservationByRoom(room);
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public List<ReservationResponseDto> getReservationsByResponsibleName(String name) {
        List<Reservation> reservations = repository.findReservationByResponsibleName(name);
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public List<ReservationResponseDto> getReservationsBetween(LocalDate checkin, LocalDate checkout) {
        List<Reservation> reservations = repository.findReservationBetween(checkin, checkout);
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public ReservationResponseDto updateReservation(ReservationUpdateDto update) {
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

        return ReservationMapper.INSTANCE.toDto(repository.save(reservation));
    }

    @Override
    public void deleteReservation(String id) {
        repository.deleteById(id);
    }
}
