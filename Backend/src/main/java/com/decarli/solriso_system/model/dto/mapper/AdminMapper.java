package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.request.AdminCreateDto;
import com.decarli.solriso_system.model.dto.request.AdminLoginDto;
import com.decarli.solriso_system.model.dto.response.AdminResponseDto;
import com.decarli.solriso_system.model.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    UserEntity toAdmin(AdminCreateDto dto);

    UserEntity toAdmin(AdminLoginDto dto);

    AdminResponseDto toResponseDto(UserEntity userEntity);

    List<AdminResponseDto> toDtoList(List<UserEntity> userEntities);
}
