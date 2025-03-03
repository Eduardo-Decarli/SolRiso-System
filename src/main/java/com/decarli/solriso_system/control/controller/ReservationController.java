package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@RequestBody ReservationCreateDto reservation) {
        ReservationResponseDto r = reservationService.createReservation(reservation);
        return ResponseEntity.ok().body(r);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservationsByToday() {
        return null;
    }

    @GetMapping("/byRoom")
    public ResponseEntity<List<Reservation>> getReservationByRoom(@PathVariable int roomNumber) {
        return null;
    }

    @GetMapping("/byResponsibleName")
    public ResponseEntity<List<Reservation>> getReservationByResponsibleName(@PathVariable String responsableName) {
        return null;
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationUpdateDto reservation) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Reservation> deleteReservation(@PathVariable UUID id) {
        return null;
    }

}
