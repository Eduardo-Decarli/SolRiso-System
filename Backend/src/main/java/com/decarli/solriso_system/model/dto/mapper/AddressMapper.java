package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.AddressCreateDto;
import com.decarli.solriso_system.model.dto.response.AddressResponseDto;
import com.decarli.solriso_system.model.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressCreateDto dto);

    AddressResponseDto toDto(Address address);
}
