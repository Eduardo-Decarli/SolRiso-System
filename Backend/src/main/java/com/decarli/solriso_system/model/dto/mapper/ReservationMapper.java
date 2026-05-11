package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.response.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.request.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.ReservationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" ,uses = { AdminMapper.class, GuestMapper.class })
public interface ReservationMapper {

    ReservationEntity toReservation(ReservationCreateDto dto);

    ReservationEntity toReservation(ReservationUpdateDto dto);

    List<ReservationEntity> toReservationList(List<ReservationCreateDto> dtoList);

    ReservationResponseDto toDto(ReservationEntity reservationEntity);

    List<ReservationResponseDto> toDtoList(List<ReservationEntity> reservationEntities);
}
