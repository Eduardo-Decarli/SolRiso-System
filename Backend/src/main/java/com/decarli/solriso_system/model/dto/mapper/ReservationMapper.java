package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.response.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.request.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" ,uses = { AdminMapper.class, ResponsibleBookingMapper.class })
public interface ReservationMapper {

    Reservation toReservation(ReservationCreateDto dto);

    Reservation toReservation(ReservationUpdateDto dto);

    List<Reservation> toReservationList(List<ReservationCreateDto> dtoList);

    ReservationResponseDto toDto(Reservation reservation);

    List<ReservationResponseDto> toDtoList(List<Reservation> reservations);
}
