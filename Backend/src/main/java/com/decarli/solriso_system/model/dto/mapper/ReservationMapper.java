package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" ,uses = { AdminMapper.class, ResponsibleBookingMapper.class })
public interface ReservationMapper {

    Reservation toReservation(ReservationCreateDto dto);

    Reservation toReservation(ReservationUpdateDto dto);

    List<Reservation> toReservationList(List<ReservationCreateDto> dtoList);

    ReservationResponseDto toDto(Reservation reservation);

    List<ReservationResponseDto> toDtoList(List<Reservation> reservations);
}
