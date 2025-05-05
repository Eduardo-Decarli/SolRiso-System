package com.decarli.solriso_system.control.service;

import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.security.Admin;

public interface AdminService {

    void register(AdminCreateDto adminCreateDto);
    String login(String email, String password);
    Admin getAdminById(String id);
    Admin getAdminByEmail(String email);
}
