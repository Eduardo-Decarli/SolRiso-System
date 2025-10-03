package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.AdminCreateDto;
import com.decarli.solriso_system.model.dto.request.AdminLoginDto;
import com.decarli.solriso_system.model.dto.response.AdminResponseDto;
import com.decarli.solriso_system.model.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    User toAdmin(AdminCreateDto dto);

    User toAdmin(AdminLoginDto dto);

    AdminResponseDto toResponseDto(User user);

    List<AdminResponseDto> toDtoList(List<User> users);
}
