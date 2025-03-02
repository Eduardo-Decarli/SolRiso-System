package com.decarli.solriso_system.model.control.controller;

import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservationsByToday() {
        return null;
    }

    @GetMapping("/byRoom")
    public ResponseEntity<List<Reservation>> getReservationByRoom(@PathVariable int roomNumber) {
        return null;
    }

    @GetMapping("/byResponsableName")
    public ResponseEntity<List<Reservation>> getReservationByResponsableName(@PathVariable String responsableName) {
        return null;
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Reservation> deleteReservation(@PathVariable UUID id) {
        return null;
    }

}
