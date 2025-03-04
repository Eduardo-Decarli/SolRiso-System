package com.decarli.solriso_system.control.controller;

import com.decarli.solriso_system.control.service.ReservationService;
import com.decarli.solriso_system.model.dto.ReservationCreateDto;
import com.decarli.solriso_system.model.dto.ReservationResponseDto;
import com.decarli.solriso_system.model.dto.ReservationUpdateDto;
import com.decarli.solriso_system.model.entities.Reservation;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(@RequestBody ReservationCreateDto reservation) {
        ReservationResponseDto response = service.createReservation(reservation);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByToday() {
        List<ReservationResponseDto> response = service.getReservationsToday();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/byRoom")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByRoom(@RequestParam int roomNumber) {
        List<ReservationResponseDto> response = service.getReservationsByRoom(roomNumber);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/byResponsibleName")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByResponsibleName(@RequestParam String responsableName) {
        List<ReservationResponseDto> response = service.getReservationsByResponsibleName(responsableName);
        return ResponseEntity.ok().body(response);
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
