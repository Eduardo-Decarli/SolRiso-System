package com.decarli.solriso_system.model.dto.mapper;

import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.admin.AdminLoginDto;
import com.decarli.solriso_system.model.dto.admin.AdminResponseDto;
import com.decarli.solriso_system.model.security.Admin;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toAdmin(AdminCreateDto dto);

    Admin toAdmin(AdminLoginDto dto);

    AdminResponseDto toResponseDto(Admin admin);

    List<AdminResponseDto> toDtoList(List<Admin> admins);
}
