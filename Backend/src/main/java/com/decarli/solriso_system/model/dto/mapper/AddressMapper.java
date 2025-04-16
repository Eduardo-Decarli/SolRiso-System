package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.address.AddressCreateDto;
import com.decarli.solriso_system.model.dto.address.AddressResponseDto;
import com.decarli.solriso_system.model.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressCreateDto dto);

    AddressResponseDto toDto(Address address);
}
