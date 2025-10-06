package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.ResponsibleBookingCreateDto;
import com.decarli.solriso_system.model.entities.GuestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsibleBookingMapper {

    GuestEntity toResponsibleBooking(ResponsibleBookingCreateDto dto);
}
