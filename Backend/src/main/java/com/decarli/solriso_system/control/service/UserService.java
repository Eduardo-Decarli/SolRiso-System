package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.request.AdminCreateDto;
import com.decarli.solriso_system.model.entities.UserEntity;

import java.util.List;

public interface UserService {

    void register(AdminCreateDto adminCreateDto);
    String login(String email, String password);
    UserEntity getAdminById(Long id);
    UserEntity getAdminByEmail(String email);
    List<UserEntity> getAllAdmins();
    void forgotPassword(String email, String password);
}
