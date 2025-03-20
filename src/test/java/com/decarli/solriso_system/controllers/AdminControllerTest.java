package com.decarli.solriso_system.controllers;

import com.decarli.solriso_system.control.controller.AdminController;
import com.decarli.solriso_system.control.service.impl.AuthService;
import com.decarli.solriso_system.model.dto.admin.AdminCreateDto;
import com.decarli.solriso_system.model.dto.admin.AdminLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private AdminCreateDto validAdminCreateDto;
    private AdminLoginDto validAdminLoginDto;

    @BeforeEach
    void setUp() {
        validAdminCreateDto = new AdminCreateDto();
        validAdminCreateDto.setName("John Doe");
        validAdminCreateDto.setEmail("john@example.com");
        validAdminCreateDto.setPassword("ValidPass123!");
        validAdminCreateDto.setRole("ADMIN");

        validAdminLoginDto = new AdminLoginDto();
        validAdminLoginDto.setEmail("john@example.com");
        validAdminLoginDto.setPassword("ValidPass123!");
    }

    @Test
    void testRegister_Success() throws Exception {

        String url = "http://localhost:" + port + "/auth/register";

        String response = restTemplate.postForObject(url, validAdminCreateDto, String.class);

        assertEquals("Admin john@example.com registered successfully", response);
    }


}