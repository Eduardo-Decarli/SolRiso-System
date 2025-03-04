package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.ReservationRepository;
import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.Reservation;
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
    public List<ReservationResponseDto> getReservationsToday() {
        List<Reservation> reservations = repository.findReservationByCheckin(LocalDate.now());
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public List<ReservationResponseDto> getReservationsByRoom(int roomNumber) {
        List<Reservation> reservations = repository.findReservationByRoomNumber(roomNumber);
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public List<ReservationResponseDto> getReservationsByResponsibleName(String name) {
        List<Reservation> reservations = repository.findReservationByResponsibleName(name);
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public List<ReservationResponseDto> getReservationsBetween(LocalDate checkin, LocalDate checkout) {
        List<Reservation> reservations = repository.findReservationByCheckinAfterAndCheckoutBefore(checkin, checkout);
        return ReservationMapper.INSTANCE.toDtoList(reservations);
    }

    @Override
    public ReservationResponseDto updateReservation(ReservationUpdateDto reservation) {
        return null;
    }

    @Override
    public void deleteReservation(Reservation reservation) {

    }
}
