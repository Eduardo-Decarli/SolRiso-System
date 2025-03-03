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

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationResponseDto createReservation(ReservationCreateDto reservation) {
        Reservation r = reservationRepository.save(ReservationMapper.INSTANCE.toReservation(reservation));
        return ReservationMapper.INSTANCE.toDto(r);
    }

    @Override
    public ReservationResponseDto getReservationByResponsibleName(String name) {
        return null;
    }

    @Override
    public List<ReservationResponseDto> getReservationsToday() {
        return List.of();
    }

    @Override
    public List<ReservationResponseDto> getReservationsByRoom(int roomNumber) {
        return List.of();
    }

    @Override
    public List<ReservationResponseDto> getReservationsBetween(LocalDate checkin, LocalDate checkout) {
        return List.of();
    }

    @Override
    public ReservationResponseDto updateReservation(ReservationUpdateDto reservation) {
        return null;
    }

    @Override
    public void deleteReservation(Reservation reservation) {

    }
}
