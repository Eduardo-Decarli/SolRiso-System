package com.decarli.solriso_system.control.service.impl;

import com.decarli.solriso_system.control.repositories.UserRepository;
import com.decarli.solriso_system.control.service.UserService;
import com.decarli.solriso_system.model.dto.request.AdminCreateDto;
import com.decarli.solriso_system.model.dto.mapper.AdminMapper;
import com.decarli.solriso_system.model.dto.response.JwtResponse;
import com.decarli.solriso_system.model.entities.UserEntity;
import com.decarli.solriso_system.model.exceptions.AdminNotFoundException;
import com.decarli.solriso_system.model.exceptions.UserAlreadyExistsException;
import com.decarli.solriso_system.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, AdminMapper adminMapper, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminMapper = adminMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(AdminCreateDto create) {
        if (userRepository.existsByEmail(create.getEmail())) {
            logger.error("User {} can't be registered, it's already exist", create);
            throw new UserAlreadyExistsException("Usuário já está registrado no sistema");
        }

        UserEntity userEntity = adminMapper.toAdmin(create);
        userEntity.setEmail(create.getEmail().toLowerCase());
        userEntity.setPassword(passwordEncoder.encode(create.getPassword()));
        userRepository.save(userEntity);
        logger.info("User {} created successfully", userEntity);
    }

    public JwtResponse login(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        JwtResponse token = jwtService.generateToken((UserEntity) authentication.getPrincipal());
        logger.info("User {} did login successfully", email);
        return token;
    }

    public UserEntity getAdminById(Long id) {
        logger.info("Looking for admin by id {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin não encontrado com o id: " + id));
    }

    public UserEntity getAdminByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity != null) {
            logger.info("Found user {} by email {}", userEntity, email);
            return userEntity;
        }
        return null;
    }

    @Override
    public List<UserEntity> getAllAdmins() {
        logger.info("Looking for all admins");
        return userRepository.findAll();
    }

    @Override
    public void forgotPassword(String email, String password) {
        UserEntity adm = getAdminByEmail(email);
        adm.setPassword(passwordEncoder.encode(password));
        userRepository.save(adm);
        logger.info("User {} changed his password", email);
    }
}
