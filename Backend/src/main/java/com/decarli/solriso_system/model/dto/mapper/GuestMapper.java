package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.ResponsibleBookingCreateDto;
import com.decarli.solriso_system.model.dto.response.GuestDto;
import com.decarli.solriso_system.model.entities.GuestEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    GuestEntity toGuestEntity(ResponsibleBookingCreateDto dto);
    List<GuestDto> toListDTO(List<GuestEntity> entities);
}
