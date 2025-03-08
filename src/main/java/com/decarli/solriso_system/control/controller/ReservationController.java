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

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@RequestBody @Valid ReservationCreateDto reservation) {
        ReservationResponseDto response = service.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByToday() {
        List<ReservationResponseDto> response = service.getReservationsToday();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/id")
    public ResponseEntity<ReservationResponseDto> getReservationsById(@RequestParam String id) {
        Reservation response = service.getReservationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ReservationMapper.INSTANCE.toDto(response));
    }

    @GetMapping("/byRoom")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByRoom(@RequestParam int room) {
        List<ReservationResponseDto> response = service.getReservationsByRoom(room);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/byResponsibleName")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByResponsibleName(@RequestParam String responsableName) {
        List<ReservationResponseDto> response = service.getReservationsByResponsibleName(responsableName);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/byBetween")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByBetween(@RequestParam LocalDate checkin, @RequestParam LocalDate checkout) {
        List<ReservationResponseDto> response = service.getReservationsBetween(checkin, checkout);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<ReservationResponseDto> updateReservation(@RequestBody @Valid ReservationUpdateDto reservation) {
        ReservationResponseDto response = service.updateReservation(reservation);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReservation(@RequestParam String id) {
        service.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
