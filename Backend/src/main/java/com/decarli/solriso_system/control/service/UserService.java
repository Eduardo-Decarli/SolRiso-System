package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.request.AdminCreateDto;
import com.decarli.solriso_system.model.entities.User;

import java.util.List;

public interface UserService {

    void register(AdminCreateDto adminCreateDto);
    String login(String email, String password);
    User getAdminById(Long id);
    User getAdminByEmail(String email);
    List<User> getAllAdmins();
    void forgotPassword(String email, String password);
}
