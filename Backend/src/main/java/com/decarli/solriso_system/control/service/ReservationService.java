package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.request.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.request.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.ReservationEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    @Transactional
    void createReservation(ReservationCreateDto create);
    ReservationEntity getReservationById(Long id);
    List<ReservationEntity> getReservationsToday();
    List<ReservationEntity> getReservationsByRoom(int room);
    List<ReservationEntity> getReservationsByResponsibleName(String name);
    List<ReservationEntity> getReservationsBetween(LocalDate checkin, LocalDate checkout);
    @Transactional
    ReservationEntity updateReservation(ReservationUpdateDto update);
    List<ReservationEntity> getAllReservations();
    void deleteReservation(Long id);
}
