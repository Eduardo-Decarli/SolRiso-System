package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.responsibleBooking.ResponsibleBookingCreateDto;
import com.decarli.solriso_system.model.entities.ResponsibleBooking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsibleBookingMapper {

    ResponsibleBooking toResponsibleBooking(ResponsibleBookingCreateDto dto);
}
