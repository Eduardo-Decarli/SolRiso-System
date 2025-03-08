package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.AdminRepository;
import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.mapper.AdminMapper;
import com.decarli.solriso_system.model.entities.security.Admin;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;
    private final AuthenticationManager authenticationManager;

    public AuthService(com.decarli.solriso_system.control.repositories.AdminRepository adminRepository, PasswordEncoder passwordEncoder, com.decarli.solriso_system.model.dto.mapper.AdminMapper adminMapper, AuthenticationManager authenticationManager) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminMapper = adminMapper;
        this.authenticationManager = authenticationManager;
    }

    public String register(AdminCreateDto adminCreateDto) {
        if(adminRepository.existsByEmail(adminCreateDto.getEmail())) throw new RuntimeException("This user already exists by email!");

        Admin admin = adminMapper.toAdmin(adminCreateDto);
        admin.setPassword(passwordEncoder.encode(adminCreateDto.getPassword()));
        adminRepository.save(admin);
        return "Admin " + admin.getEmail() + " registered successfully";
    }

    public String login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User " + email + " logged in successfully";
    }
}
