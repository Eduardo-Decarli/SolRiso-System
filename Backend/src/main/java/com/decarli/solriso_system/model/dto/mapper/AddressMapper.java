package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.AddressCreateDto;
import com.decarli.solriso_system.model.dto.response.AddressResponseDto;
import com.decarli.solriso_system.model.entities.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity toAddress(AddressCreateDto dto);

    AddressResponseDto toDto(AddressEntity addressEntity);
}
