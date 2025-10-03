package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT * FROM RESERVATIONS r WHERE r.checkin = :today", nativeQuery = true)
    List<Reservation> findReservationsToday(@Param("today") LocalDate today);
    List<Reservation> findReservationByRoom(int room);
    @Query(value = "SELECT * FROM RESERVATIONS r WHERE r.checkin <= r.checkout", nativeQuery = true)
    List<Reservation> findReservationsBetween(LocalDate checkin, LocalDate checkout);
    List<Reservation> findReservationByResponsibleName(String responsibleName);
    Reservation findReservationById(Long id);
}
