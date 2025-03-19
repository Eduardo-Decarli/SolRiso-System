package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.reservation.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.reservation.ReservationUpdateDto;
import com.decarli.solriso_system.model.dto.mapper.ReservationMapper;
import com.decarli.solriso_system.model.entities.Reservation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService service;
    private final ReservationMapper mapper;

    public ReservationController(ReservationService service, ReservationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@Valid @RequestBody ReservationCreateDto reservation) {
        Reservation response = service.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(response));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByToday() {
        List<Reservation> response = service.getReservationsToday();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @GetMapping("/id")
    public ResponseEntity<ReservationResponseDto> getReservationsById(@RequestParam String id) {
        Reservation response = service.getReservationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(response));
    }

    @GetMapping("/allReservations")
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        List<Reservation> response = service.getAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @GetMapping("/byRoom")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByRoom(@RequestParam int room) {
        List<Reservation> response = service.getReservationsByRoom(room);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @GetMapping("/byResponsibleName")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByResponsibleName(@RequestParam String name) {
        List<Reservation> response = service.getReservationsByResponsibleName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.toDtoList(response));
    }

    @GetMapping("/byBetween")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByBetween(@RequestParam LocalDate checkin, @RequestParam LocalDate checkout) {
        List<Reservation> response = service.getReservationsBetween(checkin, checkout);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(response));
    }

    @PutMapping
    public ResponseEntity<ReservationResponseDto> updateReservation(@RequestBody @Valid ReservationUpdateDto reservation) {
        Reservation response = service.updateReservation(reservation);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(response));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReservation(@RequestParam String id) {
        service.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
