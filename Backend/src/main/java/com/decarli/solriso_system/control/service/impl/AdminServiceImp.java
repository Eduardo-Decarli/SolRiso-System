package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.AdminRepository;
import com.decarli.solriso_system.control.service.AdminService;
import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.mapper.AdminMapper;
import com.decarli.solriso_system.model.entities.Admin;
import com.decarli.solriso_system.model.exceptions.AdminNotFoundException;
import com.decarli.solriso_system.model.exceptions.UserAlreadyExistsException;
import com.decarli.solriso_system.model.security.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    public static final Logger logger = LoggerFactory.getLogger(AdminServiceImp.class);


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
            logger.error("User {} can't be registered, it's already exist", create);
            throw new UserAlreadyExistsException("Usuário já está registrado no sistema");
        }

        Admin admin = adminMapper.toAdmin(create);
        admin.setEmail(create.getEmail().toLowerCase());
        admin.setPassword(passwordEncoder.encode(create.getPassword()));
        adminRepository.save(admin);
        logger.info("User {} created successfully", admin);
    }

    public String login(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generateToken((Admin) authentication.getPrincipal());
        logger.info("User {} did login successfully", email);
        return token;
    }

    public Admin getAdminById(String id) {
        logger.info("Looking for admin by id {}", id);
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin não encontrado com o id: " + id));
    }

    public Admin getAdminByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email);
        if(admin != null) {
            logger.info("Found user {} by email {}", admin, email);
            return admin;
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        logger.info("Looking for all admins");
        return adminRepository.findAll();
    }

    @Override
    public void forgotPassword(String email, String password) {
        Admin adm = getAdminByEmail(email);
        adm.setPassword(passwordEncoder.encode(password));
        adminRepository.save(adm);
        logger.info("User {} changed his password", email);
    }
}
