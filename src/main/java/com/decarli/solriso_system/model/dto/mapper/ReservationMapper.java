package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(target = "id", ignore = true)
    Reservation toReservation(ReservationCreateDto dto);

    @Mapping(target = "id", ignore = true)
    Reservation toReservation(ReservationUpdateDto dto);

    ReservationResponseDto toDto(Reservation reservation);
}
