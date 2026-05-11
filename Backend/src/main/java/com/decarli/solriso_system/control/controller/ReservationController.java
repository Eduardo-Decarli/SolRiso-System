package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.request.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.response.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.request.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.ReservationEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private ReservationMapper mapper;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@Valid @RequestBody ReservationCreateDto create) {
        service.createReservation(create);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByToday() {
        List<ReservationEntity> response = service.getReservationsToday();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @GetMapping("/id")
    public ResponseEntity<ReservationResponseDto> getReservationsById(@RequestParam Long id) {
        ReservationEntity response = service.getReservationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(response));
    }

    @GetMapping("/allReservations")
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        List<ReservationEntity> response = service.getAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @GetMapping("/byRoom")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByRoom(@RequestParam int room) {
        List<ReservationEntity> response = service.getReservationsByRoom(room);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @GetMapping("/byResponsibleName")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByResponsibleName(@RequestParam String name) {
        List<ReservationEntity> response = service.getReservationsByResponsibleName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.toDtoList(response));
    }

    @GetMapping("/byBetween")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByBetween(@RequestParam LocalDate checkin, @RequestParam LocalDate checkout) {
        List<ReservationEntity> response = service.getReservationsBetween(checkin, checkout);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @PutMapping
    public ResponseEntity<ReservationResponseDto> updateReservation(@RequestBody @Valid ReservationUpdateDto reservation) {
        ReservationEntity response = service.updateReservation(reservation);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(response));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReservation(@RequestParam Long id) {
        service.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
