package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.AdminRepository;
import com.decarli.solriso_system.control.service.AdminService;
import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.admin.AdminLoginDto;
import com.decarli.solriso_system.model.dto.mapper.AdminMapper;
import com.decarli.solriso_system.model.entities.Admin;
import com.decarli.solriso_system.model.exceptions.AdminNotFoundException;
import com.decarli.solriso_system.model.exceptions.UserAlreadyExistsException;
import com.decarli.solriso_system.model.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AdminServiceImp(AdminRepository adminRepository, PasswordEncoder passwordEncoder, AdminMapper adminMapper, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminMapper = adminMapper;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public void register(AdminCreateDto create) {
        if (adminRepository.existsByEmail(create.getEmail())) {
            throw new UserAlreadyExistsException("Usuário já está registrado no sistema");
        }

        Admin admin = adminMapper.toAdmin(create);
        admin.setEmail(create.getEmail().toLowerCase());
        admin.setPassword(passwordEncoder.encode(create.getPassword()));
        adminRepository.save(admin);
    }

    public String login(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generateToken((Admin) authentication.getPrincipal());

        return token;
    }

    public Admin getAdminById(String id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin não encontrado com o id: " + id));
    }

    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public void forgotPassword(AdminLoginDto dto) {
        Admin adm = adminRepository.findByEmail(dto.getEmail());
        adm.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminRepository.save(adm);
    }
}
