package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.GuestService;
import com.decarli.solriso_system.model.dto.mapper.GuestMapper;
import com.decarli.solriso_system.model.dto.response.GuestDto;
import com.decarli.solriso_system.model.entities.GuestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guest")
public class ResponsibleController {

    @Autowired
    private GuestService guestService;
    @Autowired
    private GuestMapper mapper;

    @GetMapping("/guestsByName")
    public ResponseEntity<List<GuestDto>> getGuestsByName(@RequestParam String name) {
        List<GuestEntity> guests = guestService.autocompleteResponsibleByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toListDTO(guests));
    }
}
