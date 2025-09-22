package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.request.AdminCreateDto;
import com.decarli.solriso_system.model.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void register(AdminCreateDto adminCreateDto);
    String login(String email, String password);
    User getAdminById(UUID id);
    User getAdminByEmail(String email);
    List<User> getAllAdmins();
    void forgotPassword(String email, String password);
}
